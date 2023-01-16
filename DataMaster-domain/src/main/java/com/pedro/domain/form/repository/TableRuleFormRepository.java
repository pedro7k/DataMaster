package com.pedro.domain.form.repository;

import com.pedro.domain.form.model.vo.RuleWeightVO;
import com.pedro.domain.form.model.vo.TableDetailFormVO;
import com.pedro.domain.form.model.vo.TableRuleFormVO;
import com.pedro.domain.form.model.vo.TableWeightVO;

import java.util.List;

/**
 * 约束管理页表单仓储服务
 */
public interface TableRuleFormRepository {

    /**
     * 拉取表单
     */
    List<TableRuleFormVO> loadTableRuleForm(int tid);

    /**
     * 通过cid获取列名
     */
    String queryColumnNameByCid(int cid);

    /**
     * 通过rid检查是否有报警
     */
    boolean queryIfExistAlarmByRid(int rid);

    /**
     * 编辑约束权重
     */
    int editRuleWeight(RuleWeightVO ruleWeightVO);

    /**
     * 删除指定约束的报警
     */
    void deleteAlarmByRid(List<Integer> ridList);

    /**
     * 删除指定约束
     */
    int deleteRule(List<Integer> ridList);

}
