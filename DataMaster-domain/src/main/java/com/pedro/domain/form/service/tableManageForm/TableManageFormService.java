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
}
