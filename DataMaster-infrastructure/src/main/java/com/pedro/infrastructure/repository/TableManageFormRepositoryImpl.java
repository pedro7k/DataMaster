package com.pedro.infrastructure.repository;

import com.pedro.domain.form.model.vo.TableBaseInfoVO;
import com.pedro.domain.form.repository.TableManageFormRepository;
import com.pedro.infrastructure.dao.TableAlarmDao;
import com.pedro.infrastructure.dao.TableHealthScoreDao;
import com.pedro.infrastructure.dao.TableInfoDao;
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
}
