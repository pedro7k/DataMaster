package com.pedro.infrastructure.repository;

import com.pedro.domain.form.model.vo.AlarmStateVO;
import com.pedro.domain.form.model.vo.TableAlarmFormVO;
import com.pedro.domain.form.model.vo.TableRuleBaseVO;
import com.pedro.domain.form.model.vo.TableRuleFormVO;
import com.pedro.domain.form.repository.TableAlarmFormRepository;
import com.pedro.infrastructure.dao.TableAlarmDao;
import com.pedro.infrastructure.dao.TableRuleDao;
import com.pedro.infrastructure.po.TableAlarmPO;
import com.pedro.infrastructure.po.TableRulePO;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TableAlarmFormRepositoryImpl implements TableAlarmFormRepository {

    @Resource
    private TableAlarmDao tableAlarmDao;

    @Resource
    private TableRuleDao tableRuleDao;

    @Override
    public List<TableAlarmFormVO> loadTableAlarmForm(int tid) {

        // 1.读取数据
        List<TableAlarmPO> tableAlarmPOList = tableAlarmDao.queryAlarmByTid(tid);

        // 2.对象转换
        List<TableAlarmFormVO> tableAlarmFormVOList = new ArrayList<>();
        for (TableAlarmPO tableAlarmPO : tableAlarmPOList) {
            TableAlarmFormVO oneRes = new TableAlarmFormVO();
            oneRes.setAid(tableAlarmPO.getAid());
            oneRes.setRid(tableAlarmPO.getRid());
            oneRes.setTime(tableAlarmPO.getTime());
            oneRes.setState(tableAlarmPO.getState());
            oneRes.setValue(tableAlarmPO.getValue());
            tableAlarmFormVOList.add(oneRes);
        }

        // 3.返回
        return tableAlarmFormVOList;

    }

    @Override
    public TableRuleBaseVO queryRuleByRid(int rid) {

        // 1.读取数据
        TableRulePO tableRulePO = tableRuleDao.queryRuleByRid(rid);

        // 2.对象转换
        TableRuleBaseVO tableRuleBaseVO = new TableRuleBaseVO();
        tableRuleBaseVO.setRid(tableRulePO.getRid());
        tableRuleBaseVO.setCid(tableRulePO.getCid());
        tableRuleBaseVO.setRuleWeight(tableRulePO.getRuleWeight());
        tableRuleBaseVO.setType(tableRulePO.getType());

        // 3.返回
        return tableRuleBaseVO;
    }

    @Override
    public int editAlarmState(AlarmStateVO alarmStateVO) {

        return tableAlarmDao.updateAlarmState(alarmStateVO);
    }

    @Override
    public int deleteAlarmByAid(List<Integer> aidList) {

        int deleteCount = 0;

        for (Integer aid : aidList) {
            int count = tableAlarmDao.deleteAlarmByAid(aid);
            deleteCount += count;
        }

        return deleteCount;
    }
}
