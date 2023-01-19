package com.pedro.domain.dbProcess.service.tableScan;

import com.pedro.domain.dbProcess.model.res.TableScanRes;

import java.util.List;

/**
 * 单表扫描服务
 */
public interface TableScanService {

    /**
     * 单表扫描方法
     */
    List<TableScanRes> scanTable(int tid);
}
