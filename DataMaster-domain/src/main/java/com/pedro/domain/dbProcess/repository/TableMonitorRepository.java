package com.pedro.domain.dbProcess.repository;

import com.pedro.domain.dbProcess.model.vo.MuchDeleteVO;

/**
 * 表监控仓储服务
 */
public interface TableMonitorRepository {

    /**
     * 查询某表名是否存在
     */
    boolean queryIfTableNameExist(String tableName);

    /**
     * 新大量删除操作数据插入表
     */
    void insertMuchDeleteRecord(MuchDeleteVO muchDeleteVO);
}
