package com.pedro.infrastructure.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TableHealthScoreDao {

    /**
     * 根据tid查询某表当前健康分
     */
    Double queryCurrentTableHealthScoreByTid(int tid);

    /**
     * 通过tid删除关于某表的记录
     */
    void deleteRecordByTid(int tid);
}
