package com.pedro.infrastructure.dao;

import com.pedro.domain.dbProcess.model.vo.ColumnWithTableNameVO;
import com.pedro.infrastructure.po.TableMetaInfoPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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

    /**
     * 删除指定表名的数据表
     */
    void dropTableByName(String tableName);

    /**
     * 查询数据库中所有的表名
     */
    List<String> queryTableNameList();

    /**
     * desc表
     */
    List<TableMetaInfoPO> descTable(@Param("tableName") String tableName);
}
