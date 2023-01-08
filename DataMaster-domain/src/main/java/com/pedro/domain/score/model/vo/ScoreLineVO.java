package com.pedro.domain.score.model.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * 过去七日整体健康分变化趋势
 */
public class ScoreLineVO {

    private List<Double> score;

    private List<String> date;

    public ScoreLineVO() {
        this.score = new ArrayList<>();
        this.date = new ArrayList<>();
    }

    public ScoreLineVO(List<Double> score, List<String> date) {
        this.score = score;
        this.date = date;
    }

    public List<Double> getScore() {
        return score;
    }

    public void setScore(List<Double> score) {
        this.score = score;
    }

    public List<String> getDate() {
        return date;
    }

    public void setDate(List<String> date) {
        this.date = date;
    }
}
