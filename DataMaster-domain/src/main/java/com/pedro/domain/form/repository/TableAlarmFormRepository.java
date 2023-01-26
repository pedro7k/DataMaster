package com.pedro.domain.form.repository;

import com.pedro.domain.dbProcess.model.vo.MuchDeleteVO;
import com.pedro.domain.form.model.vo.AlarmStateVO;
import com.pedro.domain.form.model.vo.TableAlarmFormVO;
import com.pedro.domain.form.model.vo.TableRuleBaseVO;
import com.pedro.domain.form.model.vo.TableRuleFormVO;

import java.util.Date;
import java.util.List;

/**
 * 报警表单仓储服务
 */
public interface TableAlarmFormRepository {

    /**
     * 拉取表单数据
     */
    List<TableAlarmFormVO> loadTableAlarmForm(int tid);

    /**
     * 通过rid查询约束信息
     */
    TableRuleBaseVO queryRuleByRid(int rid);

    /**
     * 编辑报警状态
     */
    int editAlarmState(AlarmStateVO alarmStateVO);

    /**
     * 根据aid删除报警条目
     */
    int deleteAlarmByAid(List<Integer> aidList);

    /**
     * 检查当前tid大量删除变更记录的时间,没有则返回null
     */
    MuchDeleteVO queryMuchDeleteRecord(int tid);

    /**
     * 修改短时大量删除变更报警状态
     */
    void updateMuchDeleteRecordState(MuchDeleteVO muchDeleteVO);

    /**
     * 根据tid删除大量写变更记录
     */
    void deleteMuchDeleteRecordByTid(int tid);
}
