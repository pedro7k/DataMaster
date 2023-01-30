package com.pedro.application.scheduleTask;

import com.pedro.application.mq.producer.TableScanEventProducer;
import com.pedro.application.process.impl.DataScanProcessImpl;
import com.pedro.common.config.Constants;
import com.pedro.domain.dbProcess.model.vo.TableScanFreqVO;
import com.pedro.domain.dbProcess.service.tableScan.TableScanService;
import com.pedro.domain.score.service.ScoreClearService;
import com.pedro.domain.score.service.TotalHealthScoreService;
import com.pedro.domain.support.random.RandomUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;

import static com.pedro.common.config.Constants.*;

/**
 * 表单扫描定时任务
 */
@Component
public class TableScanScheduleTask {

    private static final Logger logger = LoggerFactory.getLogger(TableScanScheduleTask.class);

    @Resource
    private TableScanEventProducer tableScanEventProducer;

    @Resource
    private TableScanService tableScanService;

    @Resource
    private TotalHealthScoreService totalHealthScoreService;

    @Resource
    private ScoreClearService scoreClearService;


    /**
     * 每5秒执行一次单表扫描，一分钟执行12次
     */
    @Scheduled(cron = TABLE_SCAN_CRON)
    private void doTableScan() {

        // 1.从tableInfo表中查询tid和扫描频率
        List<TableScanFreqVO> tableScanFreqVOList = tableScanService.queryAllTableScanFreq();

        // 2.决策是否扫描，构造tidList
        ArrayList<Integer> tidList = new ArrayList<>();
        for (TableScanFreqVO tableScanFreqVO : tableScanFreqVOList) {
            if (RandomUtil.randomIfScanByFreq(Constants.REAL_SCAN_FREQ_PER_MIN, tableScanFreqVO.getScanFreqPerMin())) {
                tidList.add(tableScanFreqVO.getTid());
            }
        }

        // 3.调用消息队列生产者
        logger.info("表单扫描定时任务执行，tidList={}", tidList);
        tableScanEventProducer.sendTableScanMessage(tidList);
    }

    /**
     * 每分钟执行一次，重打整体健康分
     */
    @Scheduled(cron = TOTAL_HEALTH_SCORE_CRON)
    private void doUpdateTotalHealthScore() {
        totalHealthScoreService.updateTotalHealthScore();
        logger.info("重打整体健康分，间隔1min");
    }

    /**
     * 每小时整点执行一次，清理过期数据
     */
    @Scheduled(cron = DELETE_HEALTH_SCORE_CRON)
    private void doDeleteScoreInOneHour(){
        scoreClearService.deleteScoreInPastOneHour();
        logger.info("清理健康分数据，间隔1h");
    }
}
