package com.pedro.domain.dbProcess.repository;

import com.pedro.domain.dbProcess.model.vo.*;

public interface TableCreationRepository {

    /**
     * 创建表
     */
    void createTable(TableCreationVO tableCreationVO);

    /**
     * 插入新表info
     */
    void insertTableInfo(TableInfoVO tableInfoVO);

    /**
     * 通过表名查询tid
     */
    int queryTidByName(String tableName);

    /**
     * 插入新表detail
     */
    void insertTableDetail(TableDetailVO tableDetailVO);

    /**
     * 通过tid和列名查询cid
     */
    int queryCidByTidAndColumnName(int tid, String columnName);

    /**
     * 插入新表rule
     */
    void insertTableRule(TableRuleVO tableRuleVO);

    /**
     * 删除指定表名的表
     */
    void dropTableByTableName(String tableName);

    /**
     * 删除指定tid的表相关的所有信息
     */
    void deleteDataByTid(int tid);

    /**
     * 添加单表健康分数据
     */
    void insertTableHealthScore(TableHealthScoreVO tableHealthScoreVO);

    /**
     * 从except表中删除对应表名的数据
     */
    void deleteTableFromExcept(String tableName);
}
