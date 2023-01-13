package com.pedro.domain.dbProcess.service.tableCreation.impl;

import com.pedro.common.enums.ServiceExceptionEnum;
import com.pedro.common.exceptions.ServiceException;
import com.pedro.domain.dbProcess.model.req.ColumnReq;
import com.pedro.domain.dbProcess.model.req.TableCreationReq;
import com.pedro.domain.dbProcess.model.vo.TableCreationColumnVO;
import com.pedro.domain.dbProcess.model.vo.TableCreationVO;
import com.pedro.domain.dbProcess.repository.TableCreationRepository;
import com.pedro.domain.dbProcess.service.tableCreation.VisualCreateTableService;
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
public class VisualCreateTableServiceImpl implements VisualCreateTableService {

    private static final Logger logger = LoggerFactory.getLogger(VisualCreateTableServiceImpl.class);

    @Resource
    TableCreationRepository tableCreationRepository;

    @Transactional
    @Override
    public void createTable(TableCreationReq req) {

        // 1.数据校验
        // 1.1 列集合为空
        if (CollectionUtils.isEmpty(req.getColumns())) {
            throw new ServiceException(ServiceExceptionEnum.EMPTY_COLUMN_LIST);
        }

        // 2.创建表
        try {
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
        } catch(ServiceException e){
            logger.error(e.getMessage());
            throw e;
        } catch (Throwable e) {
            logger.error(e.getMessage());
            throw new ServiceException(ServiceExceptionEnum.CREATE_TABLE_ERROR);
        }

        // 3.填充info表和detail表

        // 4.填充rule表

        // TODO 5.定时任务处理


    }
}
