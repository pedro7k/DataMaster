package com.pedro.domain.dbProcess.service.tableMonitor.impl;

import com.pedro.common.enums.AlarmStateEnum;
import com.pedro.domain.dbProcess.model.vo.MuchDeleteVO;
import com.pedro.domain.dbProcess.model.vo.TableAlarmVO;
import com.pedro.domain.dbProcess.repository.TableCreationRepository;
import com.pedro.domain.dbProcess.repository.TableMonitorRepository;
import com.pedro.domain.dbProcess.repository.TableScanRepository;
import com.pedro.domain.dbProcess.service.tableMonitor.TableMonitorService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class TableMonitorServiceImpl implements TableMonitorService {

    @Resource
    private TableMonitorRepository tableMonitorRepository;

    @Resource
    private TableCreationRepository tableCreationRepository;


    @Override
    public void insertMuchDeleteAlarm(String tableName) {

        // 1.检查是否在info表中
        boolean ifTableNameExist = tableMonitorRepository.queryIfTableNameExist(tableName);
        if (!ifTableNameExist){
            return;
        }

        // 2.通过表名获取tid
        int tid = tableCreationRepository.queryTidByName(tableName);

        // 3.写入much_delete表
        MuchDeleteVO muchDeleteVO = new MuchDeleteVO();
        muchDeleteVO.setTid(tid);
        muchDeleteVO.setTime(new Date());
        tableMonitorRepository.insertMuchDeleteRecord(muchDeleteVO);
    }
}
