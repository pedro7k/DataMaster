package com.pedro.domain.form.repository;

import com.pedro.domain.dbProcess.model.vo.TableRuleVO;
import com.pedro.domain.form.model.vo.*;

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

    /**
     * 获取tid对应的OptionVOList
     */
    List<OptionVO> getColumnOptionList(int tid);

    /**
     * 通过cid获取列是否是数值类型
     */
    boolean queryIfColumnIsNumByCid(int cid);

    /**
     * 插入新约束
     */
    void insertRule(TableRuleVO tableRuleVO);

}
