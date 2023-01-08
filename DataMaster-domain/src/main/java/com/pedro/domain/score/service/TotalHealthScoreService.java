package com.pedro.domain.score.service;


import com.pedro.domain.score.model.vo.ScoreLineVO;

import java.util.List;

/**
 * 整体健康分服务
 */
public interface TotalHealthScoreService {

    /**
     * 获得当前的整体健康分
     * @return
     */
    Double getCurrentTotalHealthScore();

    /**
     * 获得过去七日整体健康分变化趋势
     */
    ScoreLineVO get7DaysTotalHealthScoreLine();
}
