package com.pedro.domain.form.service.tableRuleForm;

import com.pedro.domain.form.model.res.TableDetailFormRes;
import com.pedro.domain.form.model.res.TableRuleFormRes;
import com.pedro.domain.form.model.vo.PieDataVO;

import java.util.List;

/**
 * 约束管理页表单服务
 */
public interface TableRuleFormService {

    /**
     * 表单数据拉取
     */
    List<TableRuleFormRes> loadTableRuleForm(int tid);

    /**
     * 约束类型饼图数据拉取
     */
    List<PieDataVO> loadRuleTypePie(int tid);
}
