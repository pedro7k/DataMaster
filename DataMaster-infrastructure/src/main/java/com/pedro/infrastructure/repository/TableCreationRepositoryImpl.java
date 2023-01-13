package com.pedro.infrastructure.repository;

import com.pedro.common.enums.ServiceExceptionEnum;
import com.pedro.common.exceptions.ServiceException;
import com.pedro.domain.dbProcess.model.vo.ColumnWithTableNameVO;
import com.pedro.domain.dbProcess.model.vo.TableCreationColumnVO;
import com.pedro.domain.dbProcess.model.vo.TableCreationVO;
import com.pedro.domain.dbProcess.repository.TableCreationRepository;
import com.pedro.infrastructure.dao.CommonDao;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Repository
public class TableCreationRepositoryImpl implements TableCreationRepository {

    @Resource
    private CommonDao commonDao;

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
}
