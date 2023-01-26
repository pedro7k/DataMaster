package com.pedro.infrastructure.repository;

import com.pedro.domain.dbProcess.model.vo.MuchDeleteVO;
import com.pedro.domain.dbProcess.repository.TableMonitorRepository;
import com.pedro.infrastructure.dao.CommonDao;
import com.pedro.infrastructure.dao.TableAlarmDao;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class TableMonitorRepositoryImpl implements TableMonitorRepository {

    @Resource
    private CommonDao commonDao;

    @Resource
    private TableAlarmDao tableAlarmDao;

    @Override
    public boolean queryIfTableNameExist(String tableName) {
        return commonDao.queryIfTableNameExist(tableName);
    }

    @Override
    public void insertMuchDeleteRecord(MuchDeleteVO muchDeleteVO) {
        tableAlarmDao.insertMuchDeleteRecord(muchDeleteVO);
    }

}
