package com.pedro.domain.form.repository;

import com.pedro.domain.form.model.res.TableManageFormRes;
import com.pedro.domain.form.model.vo.TableBaseInfoVO;

import java.util.List;

/**
 * TableManageForm仓储服务
 */
public interface TableManageFormRepository {

    /**
     * 查询所有表单信息
     */
    List<TableBaseInfoVO> queryTableInfo();

    /**
     * 通过tid查询单表健康分
     */
    Double queryHealthScoreByTid(int tid);

    /**
     * 通过tid查询报警数量
     */
    int queryAlarmTimesByTid(int tid);
}
