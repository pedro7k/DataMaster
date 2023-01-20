package com.pedro.application.process.impl;

import com.pedro.application.process.DataScanProcess;
import com.pedro.domain.dbProcess.model.res.TableScanRes;
import com.pedro.domain.dbProcess.service.tableScan.TableScanService;
import com.pedro.domain.score.service.TableHealthScoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class DataScanProcessImpl implements DataScanProcess {

    private static final Logger logger = LoggerFactory.getLogger(DataScanProcessImpl.class);

    @Resource
    private TableScanService tableScanService;

    @Resource
    private TableHealthScoreService tableHealthScoreService;

    @Override
    public Double tableDataScanProcess(int tid) {

        // 1.执行扫描
        List<TableScanRes> tableScanResList = tableScanService.scanTable(tid);

        // 2.单表打分
        double score;
        if (CollectionUtils.isEmpty(tableScanResList)) {
            score = 100.0;
        } else {
            score = tableHealthScoreService.doTableScore(tableScanResList);
        }

        // 3.打分结果落库
        tableHealthScoreService.insertTableHealthScore(tid, score);

        // 4.返回结果
        logger.info("执行单表扫描完成，tid={}，score={}，time={}", tid, score, new Date());
        return score;
    }
}
