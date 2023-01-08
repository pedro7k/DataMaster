package com.pedro.infrastructure.po;

import java.sql.Date;

/**
 * 某日得分po
 */
public class ScoreLinePO {

    private Double score;

    private Date time;

    public ScoreLinePO() {
    }

    public ScoreLinePO(Double score, Date time) {
        this.score = score;
        this.time = time;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
