package com.pedro.domain.score.repository;

import com.pedro.domain.score.model.vo.ScoreLineVO;

import java.util.List;

/**
 * 整体健康分仓储接口
 */
public interface TotalHealthScoreRepository {

    /**
     * 获取当前整体健康分数据
     */
    Double queryCurrentTotalHealthScore();

    /**
     * 获取过去七日健康分变化趋势
     */
    ScoreLineVO query7DaysTotalHealthScoreLine();
}
