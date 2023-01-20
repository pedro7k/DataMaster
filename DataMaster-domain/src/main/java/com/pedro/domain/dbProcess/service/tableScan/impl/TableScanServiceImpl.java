package com.pedro.domain.dbProcess.service.tableScan.impl;

import com.pedro.common.enums.AlarmStateEnum;
import com.pedro.domain.dbProcess.model.res.TableScanRes;
import com.pedro.domain.dbProcess.model.vo.TableAlarmVO;
import com.pedro.domain.dbProcess.model.vo.TableRuleVO;
import com.pedro.domain.dbProcess.model.vo.TableScanFreqVO;
import com.pedro.domain.dbProcess.model.vo.TableScanRuleVO;
import com.pedro.domain.dbProcess.repository.TableScanRepository;
import com.pedro.domain.dbProcess.service.tableScan.TableScanService;
import com.pedro.domain.support.check.ValueCheckUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.temporal.ValueRange;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TableScanServiceImpl implements TableScanService {

    private static final Logger logger = LoggerFactory.getLogger(TableScanServiceImpl.class);

    private static final int millsInOneDay = 1000 * 3600 * 24;

    @Resource
    private TableScanRepository tableScanRepository;

    @Override
    public List<TableScanRes> scanTable(int tid) {

        // 1.取出所有约束
        List<TableScanRuleVO> tableScanRuleVOList = tableScanRepository.queryRuleByTid(tid);

        // 2.获取表名
        String tableName = tableScanRepository.queryTableNameByTid(tid);

        // 3.遍历约束
        List<TableScanRes> tableScanResList = new ArrayList<>();
        for (TableScanRuleVO tableScanRuleVO : tableScanRuleVOList) {
            TableScanRes oneRes = new TableScanRes();
            // 3.1 查询对应的值
            // 3.1.1 获取列名
            String columnName = tableScanRepository.queryColumnNameByCid(tableScanRuleVO.getCid());
            // 3.1.2 通过表名和列名查询对应值
            List<String> values = tableScanRepository.queryDataByTNameAndCName(tableName, columnName);
            // 3.2 判断
            Double alarmValue = ValueCheckUtil.checkConstrainedValue(tableScanRuleVO.getType(), values
                    , tableScanRuleVO.getValueAppear()
                    , tableScanRuleVO.getValueRange()
                    , tableScanRuleVO.getAppearTimes()
                    , tableScanRuleVO.getAppearRatio());
            if (alarmValue == null) {
                // 3.2.1 通过
                oneRes.setRulePass(true);
                oneRes.setWeight(tableScanRuleVO.getRuleWeight());
            } else {
                // 3.2.2 没通过，报警
                TableAlarmVO lastAlarm = tableScanRepository.queryLastAlarmByRid(tableScanRuleVO.getRid());
                oneRes.setRulePass(false);
                if (lastAlarm == null || lastAlarm.getState() == AlarmStateEnum.FINISH_PROCESS.getState()) {
                    // 3.2.2.1 过去无报警或过去的报警已经被处理，插入新报警记录
                    TableAlarmVO newAlarm = new TableAlarmVO();
                    newAlarm.setRid(tableScanRuleVO.getRid());
                    newAlarm.setTid(tid);
                    newAlarm.setState(AlarmStateEnum.WAITING_PROCESS.getState());
                    newAlarm.setTime(new Date());
                    newAlarm.setValue(alarmValue);
                    tableScanRepository.insertTableAlarm(newAlarm);
                    oneRes.setWeight(tableScanRuleVO.getRuleWeight());
                } else {
                    // 3.2.2.2 过去有未被处理的报警，不再添加新报警记录，令权重增加，最多增加5
                    int additionDays = calAdditionalWeight(lastAlarm.getTime());
                    oneRes.setWeight(tableScanRuleVO.getRuleWeight() + additionDays);
                }
            }
            // 3.3 加入结果集
            tableScanResList.add(oneRes);
        }

        // 4.返回
        return tableScanResList;
    }

    @Override
    public List<TableScanFreqVO> queryAllTableScanFreq() {
        return tableScanRepository.queryAllTableScanFreq();
    }

    /**
     * 计算因报警未处理而增加的权重，最多增加五天
     *
     * @param lastAlarmDate
     * @return
     */
    private int calAdditionalWeight(Date lastAlarmDate) {
        int additionDays = (int) ((new Date().getTime() - lastAlarmDate.getTime()) / millsInOneDay);

        return Math.min(additionDays, 5);
    }
}

