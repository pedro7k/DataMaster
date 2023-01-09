package com.pedro.infrastructure.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ExceptTableDao {

    /**
     * 新增except表
     */
    boolean insertExceptTable(String name);
}
