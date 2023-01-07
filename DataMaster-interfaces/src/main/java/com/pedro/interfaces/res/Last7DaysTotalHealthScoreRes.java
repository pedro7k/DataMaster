package com.pedro.interfaces.res;

import java.util.List;

/**
 * 过去七日整体健康分变化趋势，用于返回给前端展示
 */
public class Last7DaysTotalHealthScoreRes {

    private List<Double> totalHealthScoreLine;

    public Last7DaysTotalHealthScoreRes() {
    }

    public Last7DaysTotalHealthScoreRes(List<Double> totalHealthScoreLine) {
        this.totalHealthScoreLine = totalHealthScoreLine;
    }

    public List<Double> getTotalHealthScoreLine() {
        return totalHealthScoreLine;
    }

    public void setTotalHealthScoreLine(List<Double> totalHealthScoreLine) {
        this.totalHealthScoreLine = totalHealthScoreLine;
    }
}
