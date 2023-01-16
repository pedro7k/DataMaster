package com.pedro.interfaces.res;

/**
 * 表单当前健康软res
 */
public class CurrentTableHealthScoreRes {

    private Double currentTableHealthScore;

    public CurrentTableHealthScoreRes() {
    }

    public CurrentTableHealthScoreRes(Double currentTableHealthScore) {
        this.currentTableHealthScore = currentTableHealthScore;
    }

    public Double getCurrentTableHealthScore() {
        return currentTableHealthScore;
    }

    public void setCurrentTableHealthScore(Double currentTableHealthScore) {
        this.currentTableHealthScore = currentTableHealthScore;
    }
}
