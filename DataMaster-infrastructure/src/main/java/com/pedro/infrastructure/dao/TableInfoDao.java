package com.pedro.infrastructure.dao;

import com.pedro.domain.form.model.vo.TableWeightVO;
import com.pedro.infrastructure.po.TableInfoPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 库表信息Dao
 */
@Mapper
public interface TableInfoDao {

    /**
     * 查询所有数据库表信息
     */
    List<TableInfoPO> queryAllTableInfo();

    /**
     * 根据tid查询库表信息
     */
    TableInfoPO queryTableInfoByTid(int tid);

    /**
     * 通过tid删除关于某表的记录
     */
    void deleteRecordByTid(int tid);

    /**
     * 编辑表单权重
     */
    int updateTableWeight(TableWeightVO tableWeightVO);

    /**
     * 插入新表信息
     */
    void insertTableInfo(TableInfoPO tableInfoPO);

    /**
     * 通过表名查询库表信息
     */
    TableInfoPO queryTableInfoByName(String name);
}
