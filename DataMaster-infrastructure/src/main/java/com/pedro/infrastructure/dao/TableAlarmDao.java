package com.pedro.infrastructure.dao;

import org.apache.ibatis.annotations.Mapper;

/**
 * 报警表Dao
 */
@Mapper
public interface TableAlarmDao {

    /**
     * 根据tid查询某表当前报警次数
     */
    int queryAlarmTimesByTid(int tid);
}
