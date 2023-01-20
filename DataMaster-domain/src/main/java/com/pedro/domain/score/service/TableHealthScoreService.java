package com.pedro.domain.score.service;

import com.pedro.domain.dbProcess.model.res.TableScanRes;
import com.pedro.domain.score.model.vo.ScoreLineVO;

import java.util.List;

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

    /**
     * 单表打分
     */
    Double doTableScore(List<TableScanRes> scanResList);

    /**
     * 插入新健康分
     */
    void insertTableHealthScore(int tid, Double score);
}
