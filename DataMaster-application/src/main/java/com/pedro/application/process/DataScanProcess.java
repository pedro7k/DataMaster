package com.pedro.application.process;

/**
 * 数据库扫描、报警、打分流程编排
 */
public interface DataScanProcess {

    /**
     * 执行单表扫描、报警、打分处理
     */
    void tableDataScanProcess(int tid);

}
