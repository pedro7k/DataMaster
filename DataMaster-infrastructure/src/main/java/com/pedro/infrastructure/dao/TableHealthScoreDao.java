package com.pedro.infrastructure.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TableHealthScoreDao {

    /**
     * 根据tid查询某表当前健康分
     */
    Double queryCurrentTableHealthScoreByTid(int tid);
}
