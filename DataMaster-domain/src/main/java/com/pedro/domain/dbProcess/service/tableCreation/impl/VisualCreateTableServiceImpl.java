package com.pedro.domain.dbProcess.service.tableCreation.impl;

import com.pedro.common.enums.AlarmStateEnum;
import com.pedro.common.enums.RuleTypeEnum;
import com.pedro.common.enums.ServiceExceptionEnum;
import com.pedro.common.exceptions.ServiceException;
import com.pedro.domain.dbProcess.model.req.ColumnReq;
import com.pedro.domain.dbProcess.model.req.RuleReq;
import com.pedro.domain.dbProcess.model.req.TableCreationReq;
import com.pedro.domain.dbProcess.model.vo.*;
import com.pedro.domain.dbProcess.repository.TableCreationRepository;
import com.pedro.domain.dbProcess.service.tableCreation.VisualCreateTableService;
import com.pedro.domain.form.repository.TableManageFormRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class VisualCreateTableServiceImpl implements VisualCreateTableService {

    private static final Logger logger = LoggerFactory.getLogger(VisualCreateTableServiceImpl.class);

    private static List<String> numTypeList = new ArrayList<>();

    static {
        numTypeList.add("int");
        numTypeList.add("float");
        numTypeList.add("double");
    }

    @Resource
    private TableCreationRepository tableCreationRepository;

    @Override
    public void createTable(TableCreationReq req) {

        try {
            // 1.数据校验
            // 1.1 列集合为空
            if (CollectionUtils.isEmpty(req.getColumns())) {
                throw new ServiceException(ServiceExceptionEnum.EMPTY_COLUMN_LIST);
            }

            // 2.创建表
            TableCreationVO tableCreationVO = new TableCreationVO();
            tableCreationVO.setTableName(req.getTableName());
            // 2.1 构造字段列表
            int primaryCount = 0;
            List<TableCreationColumnVO> columns = new ArrayList<>();
            for (String columnName : req.getColumns().keySet()) {
                ColumnReq columnReq = req.getColumns().get(columnName);
                // 构造创建表所用列对象并加入repository请求集合
                TableCreationColumnVO column = new TableCreationColumnVO();
                column.setColumnName(columnName);
                column.setDataType(columnReq.getDataType());
                column.setPrimary(columnReq.isPrimary());
                if (column.isPrimary()) {
                    primaryCount++;
                }
                column.setNotNull(columnReq.isNotNull());
                column.setHasDefaultValue(columnReq.isHasDefaultValue());
                if (column.isHasDefaultValue() && column.isPrimary()) {
                    // 主键不允许有默认值
                    throw new ServiceException(ServiceExceptionEnum.PRIMARY_KEY_WITH_DEFAULT_VALUE);
                }
                column.setDefaultValue(columnReq.getDefaultValue());
                columns.add(column);
            }
            if (primaryCount > 1) {
                // 主键重复
                throw new ServiceException(ServiceExceptionEnum.DUPLICATE_PRIMARY_KEY);
            }
            tableCreationVO.setColumns(columns);
            // 2.2 执行
            tableCreationRepository.createTable(tableCreationVO);

            // 3.填充info表，获得tid
            // 3.1 填充info表
            TableInfoVO tableInfoVO = new TableInfoVO();
            tableInfoVO.setTableName(req.getTableName());
            tableInfoVO.setTableWeight(req.getTableWeight() == 0 ? 3 : req.getTableWeight());
            tableCreationRepository.insertTableInfo(tableInfoVO);
            // 3.2 查询tid
            int tid = tableCreationRepository.queryTidByName(req.getTableName());

            // 4.遍历列集合，填充detail表和rule表
            for (String columnName : req.getColumns().keySet()) {
                // 4.1 对象构造
                ColumnReq columnReq = req.getColumns().get(columnName);
                TableDetailVO tableDetailVO = new TableDetailVO();
                tableDetailVO.setTid(tid);
                tableDetailVO.setColumnName(columnName);
                tableDetailVO.setDataType(columnReq.getDataType());
                if (columnReq.isPrimary()) {
                    tableDetailVO.setPrimary(true);
                    tableDetailVO.setAutoInc(true);
                    tableDetailVO.setUnique(true);
                }
                tableDetailVO.setWithDefaultValue(columnReq.isHasDefaultValue());
                if (numTypeList.contains(columnReq.getDataType())) {
                    tableDetailVO.setNumType(true);
                }
                // 4.2 执行details插入
                tableCreationRepository.insertTableDetail(tableDetailVO);
                // 4.3 获取cid
                int cid = tableCreationRepository.queryCidByTidAndColumnName(tid, columnName);
                // 4.4 遍历规则集合，填充rule表
                if (columnReq.getRules() == null) {
                    continue;
                }
                for (String ruleName : columnReq.getRules().keySet()) {
                    // 4.3.1 对象构造
                    RuleReq ruleReq = columnReq.getRules().get(ruleName);
                    TableRuleVO tableRuleVO = new TableRuleVO();
                    tableRuleVO.setCid(cid);
                    tableRuleVO.setTid(tid);
                    tableRuleVO.setRuleWeight(ruleReq.getRuleWeight() == 0 ? 3 : ruleReq.getRuleWeight());
                    tableRuleVO.setType(RuleTypeEnum.castTypeToInt(ruleReq.getRuleType()));
                    // 如果不是数值类型，但给出的约束是数值范围相关的，抛出异常
                    if (!tableDetailVO.isNumType()) {
                        if (tableRuleVO.getType() == RuleTypeEnum.RANGE_APPEAR_RATIO_RESTRICTION.getType()
                                || tableRuleVO.getType() == RuleTypeEnum.RANGE_APPEAR_TIMES_RESTRICTION.getType()) {
                            throw new ServiceException(ServiceExceptionEnum.RULE_TYPE_ERROR);
                        }
                    }
                    tableRuleVO.setValueAppear(ruleReq.getValueAppear());
                    tableRuleVO.setValueRange(ruleReq.getValueRange());
                    tableRuleVO.setAppearTimes(ruleReq.getAppearTimes());
                    tableRuleVO.setAppearRatio(ruleReq.getAppearRatio());
                    tableRuleVO.setExtInfo(ruleName);
                    // 4.3.2 执行rule插入
                    tableCreationRepository.insertTableRule(tableRuleVO);
                }
            }

            // TODO 5.定时任务处理，同步执行一次


        } catch (ServiceException e) {
            deleteDataByTableName(req.getTableName());
            logger.error(e.getMessage());
            throw e;
        } catch (Throwable e) {
            logger.error(e.getMessage());
            throw new ServiceException(ServiceExceptionEnum.CREATE_TABLE_ERROR);
        }
    }

    /**
     * 异常处理：彻底删除指定表名的所有数据
     */
    private void deleteDataByTableName(String tableName){

        // 1.删除表
        tableCreationRepository.dropTableByTableName(tableName);

        // 2.获取tid
        int tid = tableCreationRepository.queryTidByName(tableName);
        if (tid == 0){
            // 没查到，直接返回
            return;
        }

        // 3.借助排除方法，删除alarm表->rule表->detail表->score表->info表
        // TODO 考虑有没有机会让两个重复的方法合并
        tableCreationRepository.deleteDataByTid(tid);
    }
}
