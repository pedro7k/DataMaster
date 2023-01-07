package com.pedro.infrastructure.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 整体健康分dao
 */
@Mapper
public interface TotalHealthScoreDao {

    /**
     * 查询当前整体健康分
     * @return
     */
    Double queryCurrentTotalHealthScore();

    /**
     * 获取过去七日健康分变化趋势
     */
    List<Double> query7DaysTotalHealthScoreLine();
}
