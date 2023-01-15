package com.pedro.infrastructure.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ExceptTableDao {

    /**
     * 新增except表
     */
    boolean insertExceptTable(String name);

    /**
     * 尝试去除一个except表
     */
    void deleteExceptTable(String name);

    /**
     * 获取except表名集合
     */
    List<String> queryExceptTableNameList();
}
