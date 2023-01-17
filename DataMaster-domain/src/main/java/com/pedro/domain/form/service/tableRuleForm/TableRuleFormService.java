package com.pedro.domain.form.service.tableRuleForm;

import com.pedro.common.res.CommonResult;
import com.pedro.domain.form.model.req.RuleCreationReq;
import com.pedro.domain.form.model.res.TableDetailFormRes;
import com.pedro.domain.form.model.res.TableRuleFormRes;
import com.pedro.domain.form.model.vo.OptionVO;
import com.pedro.domain.form.model.vo.PieDataVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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

    /**
     * 编辑约束权限
     */
    void editRuleWeight(int rid, int weight);

    /**
     * 删除单个约束
     */
    void deleteRule(int rid);

    /**
     * 批量删除约束
     */
    void batchDeleteRule(List<Integer> ridList);

    /**
     * 获取当前表的列选项
     */
    List<OptionVO> getColumnOptionList(int tid);

    /**
     * 创建约束
     */
    void createRule(RuleCreationReq ruleCreationReq);


}
