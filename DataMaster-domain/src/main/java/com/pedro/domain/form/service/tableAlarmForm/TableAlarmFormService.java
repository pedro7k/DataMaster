package com.pedro.domain.form.service.tableAlarmForm;

import com.pedro.domain.form.model.res.TableAlarmFormRes;
import com.pedro.domain.form.model.res.TableRuleFormRes;
import com.pedro.domain.form.model.vo.PieDataVO;

import java.util.List;

/**
 * 报警管理表单服务
 */
public interface TableAlarmFormService {

    /**
     * 表单数据拉取
     */
    List<TableAlarmFormRes> loadTableAlarmForm(int tid);

    /**
     * 报警状态饼图数据拉取
     */
    List<PieDataVO> loadAlarmStatePie(int tid);

    /**
     * 编辑报警状态
     */
    void editAlarmState(int aid, String state);

    /**
     * 删除单个报警
     */
    void deleteAlarm(int aid);

    /**
     * 批量删除报警
     */
    void batchDeleteAlarm(List<Integer> aidList);
}
