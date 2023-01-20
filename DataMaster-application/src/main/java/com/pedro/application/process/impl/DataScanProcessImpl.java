package com.pedro.application.process.impl;

import com.pedro.application.process.DataScanProcess;
import com.pedro.domain.dbProcess.model.res.TableScanRes;
import com.pedro.domain.dbProcess.service.tableScan.TableScanService;
import com.pedro.domain.score.service.TableHealthScoreService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DataScanProcessImpl implements DataScanProcess {

    @Resource
    private TableScanService tableScanService;

    @Resource
    private TableHealthScoreService tableHealthScoreService;

    @Override
    public Double tableDataScanProcess(int tid) {

        // 1.执行扫描
        List<TableScanRes> tableScanResList = tableScanService.scanTable(tid);

        // 2.单表打分
        Double score = tableHealthScoreService.doTableScore(tableScanResList);

        // 3.打分结果落库
        tableHealthScoreService.insertTableHealthScore(tid, score);

        // 4.返回结果
        return score;
    }
}
