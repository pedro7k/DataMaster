package com.pedro.domain.dbProcess.repository;

import com.pedro.domain.dbProcess.model.vo.TableAlarmVO;
import com.pedro.domain.dbProcess.model.vo.TableRuleVO;
import com.pedro.domain.dbProcess.model.vo.TableScanFreqVO;
import com.pedro.domain.dbProcess.model.vo.TableScanRuleVO;

import java.util.Date;
import java.util.List;

/**
 * 表单扫描仓储服务
 */
public interface TableScanRepository {

    /**
     * 通过tid查询约束信息
     */
    List<TableScanRuleVO> queryRuleByTid(int tid);

    /**
     * 通过tid获取表名
     */
    String queryTableNameByTid(int tid);

    /**
     * 通过表名和列名获取数据
     */
    List<String> queryDataByTNameAndCName(String tableName, String columnName);

    /**
     * 通过cid获取表名
     */
    String queryColumnNameByCid(int cid);

    /**
     * 获取最近一次该约束的报警
     */
    TableAlarmVO queryLastAlarmByRid(int rid);

    /**
     * 插入新报警记录
     */
    void insertTableAlarm(TableAlarmVO tableAlarmVO);

    /**
     * 获取tid和扫描频率，供定时任务使用
     */
    List<TableScanFreqVO> queryAllTableScanFreq();
}
