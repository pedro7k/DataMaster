package com.pedro.domain.score.repository;

import com.pedro.domain.score.model.vo.HidTidVO;
import com.pedro.domain.score.model.vo.ScoreLineVO;
import com.pedro.domain.score.model.vo.ThIdVO;

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

    /**
     * 插入新整体健康分数据
     */
    void insertTotalHealthScore(Double score);

    /**
     * 查询过去一小时内最后一条整体健康分的thid
     */
    ThIdVO queryLastThIdInPastOneHour();

    /**
     * 删除过去一小时内健康分数据，除最后一条
     */
    void deleteTotalHealthScoreInPastOneHourExceptThId(ThIdVO thIdVO);
}
