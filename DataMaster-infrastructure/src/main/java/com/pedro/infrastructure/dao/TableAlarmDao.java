package com.pedro.infrastructure.dao;

import com.pedro.domain.dbProcess.model.vo.MuchDeleteVO;
import com.pedro.domain.dbProcess.model.vo.TableAlarmVO;
import com.pedro.domain.form.model.vo.AlarmStateVO;
import com.pedro.infrastructure.po.TableAlarmPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

/**
 * 报警表Dao
 */
@Mapper
public interface TableAlarmDao {

    /**
     * 根据tid查询某表当前报警次数
     */
    int queryAlarmTimesByTid(int tid);

    /**
     * 根据tid删除关于某表的记录
     */
    void deleteRecordByTid(int tid);

    /**
     * 根据rid查询报警记录
     */
    List<TableAlarmPO> queryAlarmByRid(int rid);

    /**
     * 根据rid删除报警记录
     */
    void deleteAlarmByRid(int rid);

    /**
     * 根据tid查询报警记录
     */
    List<TableAlarmPO> queryAlarmByTid(int tid);

    /**
     * 修改报警状态
     */
    int updateAlarmState(AlarmStateVO alarmStateVO);

    /**
     * 根据aid删除报警记录
     */
    int deleteAlarmByAid(int aid);

    /**
     * 插入新报警记录
     */
    void insertTableAlarm(TableAlarmVO tableAlarmVO);

    /**
     * 新大量删除操作数据插入表
     */
    void insertMuchDeleteRecord(MuchDeleteVO muchDeleteVO);
}
