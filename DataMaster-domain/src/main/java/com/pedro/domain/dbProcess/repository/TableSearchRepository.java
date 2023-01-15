package com.pedro.domain.dbProcess.repository;


import com.pedro.domain.dbProcess.model.vo.TableDetailVO;

import java.util.List;

/**
 * 表单搜索仓储服务
 */
public interface TableSearchRepository {

    /**
     * 查询数据库中所有的表名
     */
    List<String> queryTableNameList();

    /**
     * 获取except中的表名
     */
    List<String> queryExceptTableNameList();

    /**
     * 根据表名读取表元信息
     */
    List<TableDetailVO> queryTableDetailByName(String tableName);

    /**
     * 获取DataMaster正在管理的表名集合
     */
    List<String> queryCollectedTableName();
}
