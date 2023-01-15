package com.pedro.infrastructure.repository;

import com.pedro.domain.dbProcess.model.vo.TableDetailVO;
import com.pedro.infrastructure.po.TableMetaInfoPO;
import com.pedro.domain.dbProcess.repository.TableSearchRepository;
import com.pedro.infrastructure.dao.CommonDao;
import com.pedro.infrastructure.dao.ExceptTableDao;
import com.pedro.infrastructure.dao.TableInfoDao;
import com.pedro.infrastructure.po.TableInfoPO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TableSearchRepositoryImpl implements TableSearchRepository {

    @Resource
    private CommonDao commonDao;

    @Resource
    private ExceptTableDao exceptTableDao;

    @Resource
    private TableInfoDao tableInfoDao;

    @Override
    public List<String> queryTableNameList() {
        return commonDao.queryTableNameList();
    }

    @Override
    public List<String> queryExceptTableNameList() {
        return exceptTableDao.queryExceptTableNameList();
    }

    @Override
    public List<TableDetailVO> queryTableDetailByName(String tableName) {

        // 1. DESC table
        List<TableMetaInfoPO> tableMetaInfoPOList = commonDao.descTable(tableName);

        // 2.对象转换为 DetailVO
        List<TableDetailVO> tableDetailVOList = new ArrayList<>();
        for (TableMetaInfoPO tableMetaInfoPO : tableMetaInfoPOList) {
            TableDetailVO tableDetailVO = new TableDetailVO();
            tableDetailVO.setColumnName(tableMetaInfoPO.getField());
            tableDetailVO.setDataType(tableMetaInfoPO.getType());
            tableDetailVO.setPrimary(tableMetaInfoPO.getKey().contains("PRI"));
            tableDetailVO.setAutoInc(tableMetaInfoPO.getExtra().contains("auto_increment"));
            if (tableMetaInfoPO.getKey().contains("PRI") || tableMetaInfoPO.getKey().contains("UNI")){
                tableDetailVO.setUnique(true);
            }
            if (tableMetaInfoPO.getDefault() != null){
                tableDetailVO.setWithDefaultValue(true);
            }
            tableDetailVO.setNotNull(tableMetaInfoPO.getNull().equals("NO"));
            String type = tableMetaInfoPO.getType();
            if (type.contains("int") || type.contains("float") || type.contains("double")){
                tableDetailVO.setNumType(true);
            }
            tableDetailVOList.add(tableDetailVO);
        }

        // 3.返回
        return tableDetailVOList;
    }

    @Override
    public List<String> queryCollectedTableName() {

        // 1.查出所有info表信息
        List<TableInfoPO> tableInfoPOList = tableInfoDao.queryAllTableInfo();
        // 2.获取所有表名
        List<String> collectedTableNameList = new ArrayList<>();
        for (TableInfoPO tableInfoPO : tableInfoPOList) {
            collectedTableNameList.add(tableInfoPO.getName());
        }
        // 3.返回
        return collectedTableNameList;
    }
}
