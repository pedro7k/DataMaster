package com.pedro.domain.form.service.tableDetailForm;

import com.pedro.domain.form.model.res.TableDetailFormRes;
import com.pedro.domain.form.model.res.TableManageFormRes;

import java.util.List;

/**
 * 表单详情页 表单服务
 */
public interface TableDetailFormService {

    /**
     * 表单数据拉取
     */
    List<TableDetailFormRes> loadTableDetailForm(int tid);
}
