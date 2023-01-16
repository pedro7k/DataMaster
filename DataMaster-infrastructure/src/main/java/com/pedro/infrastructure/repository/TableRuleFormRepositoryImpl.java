package com.pedro.infrastructure.repository;

import com.pedro.domain.form.model.vo.RuleWeightVO;
import com.pedro.domain.form.model.vo.TableDetailFormVO;
import com.pedro.domain.form.model.vo.TableRuleFormVO;
import com.pedro.domain.form.repository.TableRuleFormRepository;
import com.pedro.infrastructure.dao.TableAlarmDao;
import com.pedro.infrastructure.dao.TableDetailsDao;
import com.pedro.infrastructure.dao.TableRuleDao;
import com.pedro.infrastructure.po.TableAlarmPO;
import com.pedro.infrastructure.po.TableDetailPO;
import com.pedro.infrastructure.po.TableRulePO;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TableRuleFormRepositoryImpl implements TableRuleFormRepository {

    @Resource
    private TableRuleDao tableRuleDao;

    @Resource
    private TableDetailsDao tableDetailsDao;

    @Resource
    private TableAlarmDao tableAlarmDao;

    @Override
    public List<TableRuleFormVO> loadTableRuleForm(int tid) {

        // 1.获取数据
        List<TableRulePO> tableRulePOList = tableRuleDao.queryTableRuleByTid(tid);

        // 2.转换为VO
        List<TableRuleFormVO> tableRuleFormVOList = new ArrayList<>();
        for (TableRulePO tableRulePO : tableRulePOList) {
            TableRuleFormVO oneRes = new TableRuleFormVO();
            oneRes.setRid(tableRulePO.getRid());
            oneRes.setCid(tableRulePO.getCid());
            oneRes.setRuleWeight(tableRulePO.getRuleWeight());
            oneRes.setType(tableRulePO.getType());
            oneRes.setValueAppear(tableRulePO.getValueAppear());
            oneRes.setValueRange(tableRulePO.getValueRange());
            oneRes.setAppearTimes(tableRulePO.getAppearTimes());
            oneRes.setAppearRatio(tableRulePO.getAppearRatio());
            tableRuleFormVOList.add(oneRes);
        }

        // 3.返回
        return tableRuleFormVOList;
    }

    @Override
    public String queryColumnNameByCid(int cid) {

        TableDetailPO tableDetailPO = tableDetailsDao.queryTableDetailByCid(cid);

        return tableDetailPO.getColumnName();
    }

    @Override
    public boolean queryIfExistAlarmByRid(int rid) {

        List<TableAlarmPO> tableAlarmPOList = tableAlarmDao.queryAlarmByRid(rid);

        return tableAlarmPOList.size() > 0;
    }

    @Override
    public int editRuleWeight(RuleWeightVO ruleWeightVO) {

        return tableRuleDao.updateRuleWeight(ruleWeightVO);
    }

    @Override
    public void deleteAlarmByRid(List<Integer> ridList) {

        for (Integer rid : ridList) {
            tableAlarmDao.deleteAlarmByRid(rid);
        }
    }

    @Override
    public int deleteRule(List<Integer> ridList) {

        int deleteCount = 0;

        for (Integer rid : ridList) {
            int delete = tableRuleDao.deleteRuleByRid(rid);
            deleteCount += delete;
        }

        return deleteCount;
    }
}
