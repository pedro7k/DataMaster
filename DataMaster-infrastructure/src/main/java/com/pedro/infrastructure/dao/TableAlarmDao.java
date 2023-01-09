package com.pedro.infrastructure.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 报警表Dao
 */
@Mapper
public interface TableAlarmDao {

    /**
     * 根据tid查询某表当前报警次数
     */
    int queryAlarmTimesByTid(int tid);

    /**
     * 根据tid删除关于某表的记录
     */
    void deleteRecordByTid(int tid);
}
