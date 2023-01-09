package com.pedro.infrastructure.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TableRuleDao {

    /**
     * 通过tid删除关于某表的记录
     */
    void deleteRecordByTid(int tid);
}
