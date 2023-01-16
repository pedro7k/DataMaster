package com.pedro.domain.score.service;

import com.pedro.domain.score.model.vo.ScoreLineVO;

/**
 * 表单健康分服务
 */
public interface TableHealthScoreService {

    /**
     * 获取当前表单健康分
     */
    Double getCurrentTableHealthScore(int tid);

    /**
     * 获得过去七日单表健康分变化趋势
     */
    ScoreLineVO get7DaysTableHealthScoreLine(int tid);
}
