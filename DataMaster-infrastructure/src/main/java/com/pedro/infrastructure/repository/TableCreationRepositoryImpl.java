package com.pedro.infrastructure.repository;

import com.pedro.common.config.Constants;
import com.pedro.common.enums.ServiceExceptionEnum;
import com.pedro.common.exceptions.ServiceException;
import com.pedro.domain.dbProcess.model.vo.*;
import com.pedro.domain.dbProcess.repository.TableCreationRepository;
import com.pedro.infrastructure.dao.*;
import com.pedro.infrastructure.po.TableDetailPO;
import com.pedro.infrastructure.po.TableInfoPO;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class TableCreationRepositoryImpl implements TableCreationRepository {

    @Resource
    private CommonDao commonDao;

    @Resource
    private TableInfoDao tableInfoDao;

    @Resource
    private TableDetailsDao tableDetailsDao;

    @Resource
    private TableRuleDao tableRuleDao;

    @Resource
    private TableHealthScoreDao tableHealthScoreDao;

    @Resource
    private ExceptTableDao exceptTableDao;

    // 占位符
    private static final String PLACE_HOLDER = "placeHolder";

    @Override
    @Transactional
    public void createTable(TableCreationVO tableCreationVO) {

        // 1.查询表名是否存在
        if (commonDao.queryIfTableNameExist(tableCreationVO.getTableName())) {
            throw new ServiceException(ServiceExceptionEnum.DUPLICATE_TABLE_NAME);
        }

        // 2.创建仅含占位符的表
        commonDao.createTableWithPlaceHolder(tableCreationVO.getTableName());

        // 3.添加列
        for (TableCreationColumnVO column : tableCreationVO.getColumns()) {
            ColumnWithTableNameVO columnWithTableNameVO = new ColumnWithTableNameVO();
            columnWithTableNameVO.setColumnName(column.getColumnName());
            columnWithTableNameVO.setDataType(column.getDataType());
            columnWithTableNameVO.setPrimary(column.isPrimary());
            columnWithTableNameVO.setNotNull(column.isNotNull());
            columnWithTableNameVO.setHasDefaultValue(column.isHasDefaultValue());
            columnWithTableNameVO.setDefaultValue(column.getDefaultValue());
            columnWithTableNameVO.setTableName(tableCreationVO.getTableName());
            commonDao.addColumn(columnWithTableNameVO);
        }

        // 4.删除占位符列
        commonDao.dropColumn(tableCreationVO.getTableName(), PLACE_HOLDER);

    }

    @Override
    public void insertTableInfo(TableInfoVO tableInfoVO) {

        // 1.默认扫描频率为3次每分钟，对象转换
        TableInfoPO tableInfoPO = new TableInfoPO();
        tableInfoPO.setName(tableInfoVO.getTableName());
        tableInfoPO.setTableWeight(tableInfoVO.getTableWeight());
        tableInfoPO.setScanFreqPerMin(Constants.DEFAULT_SCAN_FREQ);

        // 2.插入新行
        tableInfoDao.insertTableInfo(tableInfoPO);

    }

    @Override
    public int queryTidByName(String tableName) {
        TableInfoPO tableInfoPO = tableInfoDao.queryTableInfoByName(tableName);
        return tableInfoPO.getTid();
    }

    @Override
    public void insertTableDetail(TableDetailVO tableDetailVO) {
        tableDetailsDao.insertTableDetail(tableDetailVO);
    }

    @Override
    public int queryCidByTidAndColumnName(int tid, String columnName) {

        // 1.查询表的所有列信息
        List<TableDetailPO> tableDetailPOList = tableDetailsDao.queryTableDetailByTid(tid);

        // 2.找到其中对应列的id
        for (TableDetailPO tableDetailPO : tableDetailPOList) {
            if (tableDetailPO.getColumnName().equals(columnName)) {
                return tableDetailPO.getCid();
            }
        }

        // 3.没找到，抛异常
        throw new ServiceException(ServiceExceptionEnum.CREATE_TABLE_ERROR);
    }

    @Override
    public void insertTableRule(TableRuleVO tableRuleVO) {
        tableRuleDao.insertTableRule(tableRuleVO);
    }

    @Override
    public void dropTableByTableName(String tableName) {
        commonDao.dropTableByName(tableName);
    }

    @Override
    public void deleteDataByTid(int tid) {
        // 执行删除 rule表->detail表->score表->info表
        // 1.rule
        tableRuleDao.deleteRecordByTid(tid);
        // 2.details
        tableDetailsDao.deleteRecordByTid(tid);
        // 3.score
        tableHealthScoreDao.deleteRecordByTid(tid);
        // 4.info
        tableInfoDao.deleteRecordByTid(tid);
    }

    @Override
    public void insertTableHealthScore(TableHealthScoreVO tableHealthScoreVO) {
        tableHealthScoreDao.insertTableHealthScore(tableHealthScoreVO);
    }

    @Override
    public void deleteTableFromExcept(String tableName) {
        exceptTableDao.deleteExceptTable(tableName);
    }
}
