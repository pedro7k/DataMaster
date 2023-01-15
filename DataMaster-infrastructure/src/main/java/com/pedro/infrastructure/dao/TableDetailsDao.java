package com.pedro.infrastructure.dao;

import com.pedro.domain.dbProcess.model.vo.TableDetailVO;
import com.pedro.infrastructure.po.TableDetailPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TableDetailsDao {

    /**
     * 通过tid删除关于某表的记录
     */
    void deleteRecordByTid(int tid);

    /**
     * 插入表detail
     */
    void insertTableDetail(TableDetailVO tableDetailVO);

    /**
     * 通过tid查询对应表的所有列信息
     */
    List<TableDetailPO> queryTableDetailByTid(int tid);
}
