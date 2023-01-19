package com.pedro.application.process.impl;

import com.pedro.application.process.DataScanProcess;
import com.pedro.domain.dbProcess.model.res.TableScanRes;
import com.pedro.domain.dbProcess.service.tableScan.TableScanService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DataScanProcessImpl implements DataScanProcess {

    @Resource
    private TableScanService tableScanService;

    @Override
    public void tableDataScanProcess(int tid) {

        // 1.执行扫描
        List<TableScanRes> tableScanResList = tableScanService.scanTable(tid);
        System.out.println(tableScanResList);

        // 2.TODO 打分
    }
}
