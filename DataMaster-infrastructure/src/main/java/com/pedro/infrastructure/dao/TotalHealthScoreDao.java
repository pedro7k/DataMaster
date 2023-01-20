package com.pedro.infrastructure.dao;

import com.pedro.domain.score.model.vo.ThIdVO;
import com.pedro.infrastructure.po.ScoreLinePO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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

    /**
     * 插入一条整体健康分数据
     */
    void insertTotalHealthScore(@Param("score") Double score);

    /**
     * 删除过去一小时内健康分数据，除最后一条
     */
    void deleteTotalHealthScoreInPastOneHour(ThIdVO thIdVO);

    /**
     * 查询过去一小时内最后一条整体健康分的thid
     */
    ThIdVO queryLastThIdInPastOneHour();
}
