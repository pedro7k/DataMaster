package com.pedro.domain.form.service.tableManageForm;

import com.pedro.domain.form.model.res.TableManageFormRes;

import java.util.List;

/**
 * 库表管理表单接口
 */
public interface TableManageFormService {

    /**
     * 表单数据拉取
     */
    List<TableManageFormRes> loadTableManageForm();

    /**
     * 排除单个表单
     */
    void exceptTable(int tid);

    /**
     * 批量排除表单
     */
    void batchExceptTable(List<Integer> tidList);

    /**
     * 快速编辑表单权重
     */
    void editTableWeight(int tid, int weight);

}
