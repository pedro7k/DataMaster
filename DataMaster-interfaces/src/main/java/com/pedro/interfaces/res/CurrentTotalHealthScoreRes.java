package com.pedro.interfaces.res;

/**
 * 当前整体健康分Res，用于返回给前端展示
 */
public class CurrentTotalHealthScoreRes {

    private Double currentTotalHealthScore;

    public CurrentTotalHealthScoreRes() {
    }

    public CurrentTotalHealthScoreRes(Double currentTotalHealthScore) {
        this.currentTotalHealthScore = currentTotalHealthScore;
    }

    public Double getCurrentTotalHealthScore() {
        return currentTotalHealthScore;
    }

    public void setCurrentTotalHealthScore(Double currentTotalHealthScore) {
        this.currentTotalHealthScore = currentTotalHealthScore;
    }
}
