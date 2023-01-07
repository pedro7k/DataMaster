package com.pedro.domain.score.service.impl;

import com.pedro.common.enums.ServiceExceptionEnum;
import com.pedro.common.exceptions.ServiceException;
import com.pedro.domain.score.repository.TotalHealthScoreRepository;
import com.pedro.domain.score.service.TotalHealthScoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TotalHealthScoreServiceImpl implements TotalHealthScoreService {

    private static final Logger logger = LoggerFactory.getLogger(TotalHealthScoreServiceImpl.class);

    @Resource
    TotalHealthScoreRepository totalHealthScoreRepository;

    @Override
    public Double getCurrentTotalHealthScore() {

        // 1.获取数据
        Double currentTotalHealthScore = totalHealthScoreRepository.queryCurrentTotalHealthScore();

        // 2.校验数据
        if (currentTotalHealthScore < 0 || currentTotalHealthScore > 100) {
            logger.error("健康分数据不合法");
            throw new ServiceException(ServiceExceptionEnum.HEALTH_SCORE_ILLEGAL_ERROR);
        }

        // 3.返回
        return currentTotalHealthScore;
    }

    @Override
    public List<Double> get7DaysTotalHealthScoreLine() {

        // 1.获取数据
        List<Double> line = totalHealthScoreRepository.query7DaysTotalHealthScoreLine();

        // 2.校验数据
        // 2.1 数据量大于7，不合法
        if (line.size() > 7) {
            logger.error("健康分数据不合法");
            throw new ServiceException(ServiceExceptionEnum.HEALTH_SCORE_NUM_ERROR);
        }
        // 2.2 数据量小于7，填充0在前几天
        if (line.size() < 7) {
            for (int i = 0; i < (7 - line.size()); i++) {
                line.add(0, 0.00);
            }
        }
        // 2.3 值校验
        for (Double score : line) {
            if (score < 0 || score > 100) {
                logger.error("健康分数据不合法");
                throw new ServiceException(ServiceExceptionEnum.HEALTH_SCORE_ILLEGAL_ERROR);
            }
        }

        // 3.返回
        return line;
    }
}
