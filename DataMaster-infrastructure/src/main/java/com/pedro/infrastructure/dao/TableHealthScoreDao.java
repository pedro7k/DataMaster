package com.pedro.infrastructure.dao;

import com.pedro.domain.dbProcess.model.vo.TableHealthScoreVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TableHealthScoreDao {

    /**
     * 根据tid查询某表当前健康分
     */
    Double queryCurrentTableHealthScoreByTid(int tid);

    /**
     * 通过tid删除关于某表的记录
     */
    void deleteRecordByTid(int tid);

    /**
     * 插入一条单表健康分数据
     */
    void insertTableHealthScore(TableHealthScoreVO tableHealthScoreVO);
}
