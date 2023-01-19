package com.pedro.infrastructure.repository;

import com.pedro.domain.dbProcess.model.vo.TableAlarmVO;
import com.pedro.domain.dbProcess.model.vo.TableScanRuleVO;
import com.pedro.domain.dbProcess.repository.TableScanRepository;
import com.pedro.infrastructure.dao.*;
import com.pedro.infrastructure.po.TableAlarmPO;
import com.pedro.infrastructure.po.TableDetailPO;
import com.pedro.infrastructure.po.TableInfoPO;
import com.pedro.infrastructure.po.TableRulePO;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Repository
public class TableScanRepositoryImpl implements TableScanRepository {

    @Resource
    private TableRuleDao tableRuleDao;

    @Resource
    private TableInfoDao tableInfoDao;

    @Resource
    private TableDetailsDao tableDetailsDao;

    @Resource
    private CommonDao commonDao;

    @Resource
    private TableAlarmDao tableAlarmDao;

    @Override
    public List<TableScanRuleVO> queryRuleByTid(int tid) {

        // 1.查询
        List<TableRulePO> tableRulePOList = tableRuleDao.queryTableRuleByTid(tid);

        // 2.对象转换
        List<TableScanRuleVO> tableRuleVOList = new ArrayList<>();
        for (TableRulePO tableRulePO : tableRulePOList) {
            TableScanRuleVO oneRes = new TableScanRuleVO();
            oneRes.setRid(tableRulePO.getRid());
            oneRes.setCid(tableRulePO.getCid());
            oneRes.setTid(tableRulePO.getTid());
            oneRes.setRuleWeight(tableRulePO.getRuleWeight());
            oneRes.setType(tableRulePO.getType());
            oneRes.setValueAppear(tableRulePO.getValueAppear());
            oneRes.setValueRange(tableRulePO.getValueRange());
            oneRes.setAppearTimes(tableRulePO.getAppearTimes());
            oneRes.setAppearRatio(tableRulePO.getAppearRatio());
            oneRes.setExtInfo(tableRulePO.getExtInfo());
            tableRuleVOList.add(oneRes);
        }

        // 3.返回
        return tableRuleVOList;
    }

    @Override
    public String queryTableNameByTid(int tid) {

        TableInfoPO tableInfoPO = tableInfoDao.queryTableInfoByTid(tid);

        return tableInfoPO.getName();
    }

    @Override
    public List<String> queryDataByTNameAndCName(String tableName, String columnName) {

        return commonDao.queryDataByTNameAndCName(tableName, columnName);
    }

    @Override
    public String queryColumnNameByCid(int cid) {

        TableDetailPO tableDetailPO = tableDetailsDao.queryTableDetailByCid(cid);

        return tableDetailPO.getColumnName();
    }

    @Override
    public TableAlarmVO queryLastAlarmByRid(int rid) {

        // 1.查询
        List<TableAlarmPO> tableAlarmPOList = tableAlarmDao.queryAlarmByRid(rid);
        if (CollectionUtils.isEmpty(tableAlarmPOList)) {
            return null;
        }

        // 2.转换
        TableAlarmPO lastAlarmPO = tableAlarmPOList.get(tableAlarmPOList.size() - 1);
        TableAlarmVO tableAlarmVO = new TableAlarmVO();
        tableAlarmVO.setState(lastAlarmPO.getState());
        tableAlarmVO.setTime(lastAlarmPO.getTime());

        // 3.返回
        return tableAlarmVO;
    }

    @Override
    public void insertTableAlarm(TableAlarmVO tableAlarmVO) {

        tableAlarmDao.insertTableAlarm(tableAlarmVO);
    }

}
