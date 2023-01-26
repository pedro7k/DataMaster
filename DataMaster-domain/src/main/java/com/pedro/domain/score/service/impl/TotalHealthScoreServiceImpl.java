package com.pedro.domain.score.service.impl;

import com.pedro.common.enums.ServiceExceptionEnum;
import com.pedro.common.exceptions.ServiceException;
import com.pedro.domain.form.model.vo.TableWeightVO;
import com.pedro.domain.score.model.vo.ScoreLineVO;
import com.pedro.domain.score.repository.TableHealthScoreRepository;
import com.pedro.domain.score.repository.TotalHealthScoreRepository;
import com.pedro.domain.score.service.TotalHealthScoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TotalHealthScoreServiceImpl implements TotalHealthScoreService {

    private static final Logger logger = LoggerFactory.getLogger(TotalHealthScoreServiceImpl.class);

    @Resource
    private TotalHealthScoreRepository totalHealthScoreRepository;

    @Resource
    private TableHealthScoreRepository tableHealthScoreRepository;

    @Override
    public Double getCurrentTotalHealthScore() {

        // 1.获取数据
        Double currentTotalHealthScore = totalHealthScoreRepository.queryCurrentTotalHealthScore();

        // 2.校验数据
        if (null == currentTotalHealthScore || currentTotalHealthScore < 0 || currentTotalHealthScore > 100) {
            logger.error("健康分数据不合法");
            throw new ServiceException(ServiceExceptionEnum.HEALTH_SCORE_ILLEGAL_ERROR);
        }

        // 3.返回
        return currentTotalHealthScore;
    }

    @Override
    public ScoreLineVO get7DaysTotalHealthScoreLine() {

        // 1.获取数据
        ScoreLineVO line = totalHealthScoreRepository.query7DaysTotalHealthScoreLine();

        // 2.数据校验
        check7DaysHealthScoreLine(line);

        // 3.返回
        return line;
    }

    @Override
    public void updateTotalHealthScore() {

        // 1.查询各表的权重和tid
        List<TableWeightVO> tableWeightVOList = tableHealthScoreRepository.queryAllTableWeight();
        double totalHealthScore = 100.0;

        // 若查询结果不为空，查询各表健康分，并计算整体健康分
        if (!CollectionUtils.isEmpty(tableWeightVOList)) {
            double weightSum = 0.0, weightedResultSum = 0.0;
            for (TableWeightVO tableWeightVO : tableWeightVOList) {

                int weight = tableWeightVO.getWeight();
                double score = tableHealthScoreRepository.getCurrentTableHealthScore(tableWeightVO.getTid());
                weightedResultSum += weight * score;
                weightSum += weight;
            }
            // 原表达式分子分母各有100，省去
            totalHealthScore = weightedResultSum / weightSum;
        }

        // 3.整体健康分落库
        totalHealthScoreRepository.insertTotalHealthScore(totalHealthScore);
    }

    /**
     * 七日健康分数据校验
     *
     * @param line
     */
    public static void check7DaysHealthScoreLine(ScoreLineVO line) {
        int size = line.getDate().size();

        // 2.校验数据
        // 2.1 数据量大于7，不合法
        if (size > 7) {
            logger.error("健康分数据不合法");
            throw new ServiceException(ServiceExceptionEnum.HEALTH_SCORE_NUM_ERROR);
        }
        // 2.2 数据量小于7，填充0在前几天
        if (size < 7) {
            for (int i = 0; i < (7 - size); i++) {
                line.getScore().add(0.0);
                line.getDate().add("无记录");
            }
        }
        // 2.3 值校验
        for (Double score : line.getScore()) {
            if (score < 0 || score > 100) {
                logger.error("健康分数据不合法");
                throw new ServiceException(ServiceExceptionEnum.HEALTH_SCORE_ILLEGAL_ERROR);
            }
        }
    }
}
