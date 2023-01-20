package com.pedro.domain.score.service;

public interface ScoreClearService {

    /**
     * 清除过去一小时内的过期健康分数据，仅保留最新的一个
     */
    void deleteScoreInPastOneHour();
}
