package com.pedro.domain.dbProcess.model.vo;

/**
 * 单表健康分简单VO
 */
public class TableHealthScoreVO {

    private int tid;

    private double score;

    public TableHealthScoreVO() {
    }

    public TableHealthScoreVO(int tid, double score) {
        this.tid = tid;
        this.score = score;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
