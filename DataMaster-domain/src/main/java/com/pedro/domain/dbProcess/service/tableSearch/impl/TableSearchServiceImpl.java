package com.pedro.domain.dbProcess.service.tableSearch.impl;

import com.pedro.common.config.Constants;
import com.pedro.common.enums.ServiceExceptionEnum;
import com.pedro.common.exceptions.ServiceException;
import com.pedro.domain.dbProcess.model.vo.TableDetailVO;
import com.pedro.domain.dbProcess.model.vo.TableHealthScoreVO;
import com.pedro.domain.dbProcess.model.vo.TableInfoVO;
import com.pedro.domain.dbProcess.repository.TableCreationRepository;
import com.pedro.domain.dbProcess.repository.TableSearchRepository;
import com.pedro.domain.dbProcess.service.tableCreation.VisualCreateTableService;
import com.pedro.domain.dbProcess.service.tableCreation.impl.VisualCreateTableServiceImpl;
import com.pedro.domain.dbProcess.service.tableSearch.TableSearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TableSearchServiceImpl implements TableSearchService {

    private static final Logger logger = LoggerFactory.getLogger(TableSearchServiceImpl.class);

    @Resource
    private TableSearchRepository tableSearchRepository;

    @Resource
    private TableCreationRepository tableCreationRepository;

    @Resource
    private VisualCreateTableService visualCreateTableService;

    /**
     * 系统表前缀
     */
    private static final String SYS_TABLE_PREFIX = "sys_";

    @Override
    public void searchTable() {

        // 1.查询当前库中所有的表名
        List<String> tableNameList = tableSearchRepository.queryTableNameList();
        if (tableNameList.size() == 0) {
            return;
        }

        // 2.去除系统表、except表、已记录的表
        // 2.1 获取except表集合
        List<String> exceptTableNameList = tableSearchRepository.queryExceptTableNameList();
        // 2.2 获取info表中已存在的表集合
        List<String> collectedTableNameList = tableSearchRepository.queryCollectedTableName();
        // 2.3 筛选出未被统计但需要统计的表名
        List<String> unCollectedTableList = tableNameList.stream().filter(name ->
                !checkIfSysTableOrExceptTable(name, exceptTableNameList, collectedTableNameList)
        ).collect(Collectors.toList());

        // 3.读取其余表
        for (String tableName : unCollectedTableList) {
            try {
                // 3.1 插入info表
                // 3.1.1 插入
                TableInfoVO tableInfoVO = new TableInfoVO();
                tableInfoVO.setTableName(tableName);
                tableInfoVO.setTableWeight(Constants.DEFAULT_WEIGHT);
                tableCreationRepository.insertTableInfo(tableInfoVO);
                // 3.1.2 获取tid
                int tid = tableCreationRepository.queryTidByName(tableName);

                // 3.2 插入detail表
                List<TableDetailVO> tableDetailVOList = tableSearchRepository.queryTableDetailByName(tableName);
                for (TableDetailVO tableDetailVO : tableDetailVOList) {
                    tableDetailVO.setTid(tid);
                    tableCreationRepository.insertTableDetail(tableDetailVO);
                }

                // 4.创建一份单表健康分数据
                TableHealthScoreVO tableHealthScoreVO = new TableHealthScoreVO();
                tableHealthScoreVO.setTid(tid);
                tableHealthScoreVO.setScore(100);
                tableCreationRepository.insertTableHealthScore(tableHealthScoreVO);
            } catch (ServiceException e) {
                deleteDataByTableName(tableName);
                logger.error(e.getMessage());
                throw e;
            } catch (Throwable e) {
                logger.error(e.getMessage());
                throw new ServiceException(ServiceExceptionEnum.CREATE_TABLE_ERROR);
            }
        }
    }

    /**
     * 检查是否为系统表或在except表中或已被DataMaster管理
     */
    private boolean checkIfSysTableOrExceptTable(String name, List<String> exceptTableNameList, List<String> collectedTableNameList) {

        if (name.startsWith(SYS_TABLE_PREFIX) || exceptTableNameList.contains(name) || collectedTableNameList.contains(name)) {
            return true;
        }

        return false;
    }

    /**
     * 异常处理：彻底删除指定表名的所有数据，但不删除原表
     */
    private void deleteDataByTableName(String tableName){

        // 1.获取tid
        int tid = tableCreationRepository.queryTidByName(tableName);
        if (tid == 0){
            // 没查到，直接返回
            return;
        }

        // 2.借助排除方法，删除detail表->score表->info表
        tableCreationRepository.deleteDataByTid(tid);
    }

}
