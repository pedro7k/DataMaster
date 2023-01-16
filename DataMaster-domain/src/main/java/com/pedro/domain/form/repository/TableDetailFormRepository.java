package com.pedro.domain.form.repository;

import com.pedro.domain.form.model.vo.TableDetailFormVO;

import java.util.List;

/**
 * 表单详情页表单仓储服务
 */
public interface TableDetailFormRepository {

    /**
     * 拉取表单
     */
    List<TableDetailFormVO> loadTableDetailForm(int tid);

    /**
     * 检查某cid对应的自定义约束数量
     */
    Integer queryRuleCountByCid(int cid);
}
