package com.pedro.infrastructure.dao;

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
}
