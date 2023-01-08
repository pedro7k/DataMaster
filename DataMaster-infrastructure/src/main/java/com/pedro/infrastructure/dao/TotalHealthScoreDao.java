package com.pedro.infrastructure.dao;

import com.pedro.infrastructure.po.ScoreLinePO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 整体健康分Dao
 */
@Mapper
public interface TotalHealthScoreDao {

    /**
     * 查询当前整体健康分
     */
    Double queryCurrentTotalHealthScore();

    /**
     * 获取过去七日健康分变化趋势
     */
    List<ScoreLinePO> query7DaysTotalHealthScoreLine();
}
