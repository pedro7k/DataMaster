package com.pedro.domain.dbProcess.service.tableMonitor;

/**
 * 表监控服务
 */
public interface TableMonitorService {

    /**
     * 写入指定表的大量删除报警记录
     */
    void insertMuchDeleteAlarm(String tableName);
}
