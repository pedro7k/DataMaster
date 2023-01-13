package com.pedro.infrastructure.dao;

import com.pedro.domain.dbProcess.model.vo.ColumnWithTableNameVO;
import com.pedro.domain.dbProcess.model.vo.TableCreationColumnVO;
import com.pedro.domain.dbProcess.model.vo.TableCreationVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CommonDao {

    /**
     * 创建表，仅含占位符列
     */
    void createTableWithPlaceHolder(@Param("tableName") String tableName);

    /**
     * 为数据库表添加列
     */
    void addColumn(ColumnWithTableNameVO columnWithTableNameVO);

    /**
     * 为数据库表删除列
     */
    void dropColumn(String tableName, String columnName);

    /**
     * 查询某表名是否存在
     */
    boolean queryIfTableNameExist(String tableName);
}
