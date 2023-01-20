package com.pedro.domain.score.repository;

import com.pedro.domain.form.model.vo.TableWeightVO;
import com.pedro.domain.score.model.vo.HidTidVO;
import com.pedro.domain.score.model.vo.ScoreLineVO;

import java.util.List;

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

    /**
     * 插入新单表健康分数据
     */
    void insertTableHealthScore(int tid, Double score);

    /**
     * 查询各表的权重和Tid
     */
    List<TableWeightVO> queryAllTableWeight();

    /**
     * 查询各表过去一小时内最后一条数据hid
     */
    List<HidTidVO> queryLastHidInPastOneHour();

    /**
     * 删除除指定hid外过去一小时的但表健康分数据
     */
    void deleteHealthScoreInPastOneHourExceptHid(HidTidVO hidTidVO);
}
