package com.pedro.domain.score.repository;

import com.pedro.domain.score.model.vo.ScoreLineVO;

/**
 * 单表健康分仓储服务
 */
public interface TableHealthScoreRepository {

    /**
     * 获得当前表健康分
     * @return
     */
    Double getCurrentTableHealthScore(int tid);

    /**
     * 获取过去七日单表健康分变化趋势
     */
    ScoreLineVO query7DaysTableHealthScoreLine(int tid);
}
