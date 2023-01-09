package com.pedro.infrastructure.repository;

import com.pedro.domain.form.model.vo.TableBaseInfoVO;
import com.pedro.domain.form.model.vo.TableWeightVO;
import com.pedro.domain.form.repository.TableManageFormRepository;
import com.pedro.infrastructure.dao.*;
import com.pedro.infrastructure.po.TableInfoPO;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TableManageFormRepositoryImpl implements TableManageFormRepository {

    @Resource
    private TableInfoDao tableInfoDao;

    @Resource
    private TableHealthScoreDao tableHealthScoreDao;

    @Resource
    private TableAlarmDao tableAlarmDao;

    @Resource
    private ExceptTableDao exceptTableDao;

    @Resource
    private TableRuleDao tableRuleDao;

    @Resource
    private TableDetailsDao tableDetailsDao;

    @Override
    public List<TableBaseInfoVO> queryTableInfo() {

        // 1.获取数据
        List<TableInfoPO> tableInfoPOList = tableInfoDao.queryAllTableInfo();

        // 2.转换
        List<TableBaseInfoVO> tableBaseInfoVOList = new ArrayList<>();
        for (TableInfoPO tableInfoPO : tableInfoPOList) {
            TableBaseInfoVO tableBaseInfoVO = new TableBaseInfoVO();
            tableBaseInfoVO.setName(tableInfoPO.getName());
            tableBaseInfoVO.setTid(tableInfoPO.getTid());
            tableBaseInfoVO.setWeight(tableInfoPO.getTableWeight());
            tableBaseInfoVOList.add(tableBaseInfoVO);
        }

        // 3.返回
        return tableBaseInfoVOList;
    }

    @Override
    public Double queryHealthScoreByTid(int tid) {
        return tableHealthScoreDao.queryCurrentTableHealthScoreByTid(tid);
    }

    @Override
    public int queryAlarmTimesByTid(int tid) {
        return tableAlarmDao.queryAlarmTimesByTid(tid);
    }

    @Override
    public String queryTableNameByTid(int tid) {
        TableInfoPO tableInfoPO = tableInfoDao.queryTableInfoByTid(tid);

        return tableInfoPO.getName();
    }

    @Override
    public boolean insertExceptTable(String name) {
        return exceptTableDao.insertExceptTable(name);
    }

    @Override
    public void exceptTableByTid(List<Integer> tidList) {
        // 执行排除 alarm表->rule表->detail表->score表->info表
        for (Integer tid : tidList) {
            // 1.alarm
            tableAlarmDao.deleteRecordByTid(tid);
            // 2.rule
            tableRuleDao.deleteRecordByTid(tid);
            // 3.details
            tableDetailsDao.deleteRecordByTid(tid);
            // 4.score
            tableHealthScoreDao.deleteRecordByTid(tid);
            // 5.info
            tableInfoDao.deleteRecordByTid(tid);
        }
    }

    @Override
    public int editTableWeight(TableWeightVO tableWeightVO) {

        return tableInfoDao.updateTableWeight(tableWeightVO);
    }
}
