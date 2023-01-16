package com.pedro.domain.score.service.impl;

import com.pedro.common.enums.ServiceExceptionEnum;
import com.pedro.common.exceptions.ServiceException;
import com.pedro.domain.score.model.vo.ScoreLineVO;
import com.pedro.domain.score.repository.TableHealthScoreRepository;
import com.pedro.domain.score.service.TableHealthScoreService;
import com.pedro.domain.score.service.TotalHealthScoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static com.pedro.domain.score.service.impl.TotalHealthScoreServiceImpl.check7DaysHealthScoreLine;

@Service
public class TableHealthScoreServiceImpl implements TableHealthScoreService {

    private static final Logger logger = LoggerFactory.getLogger(TableHealthScoreServiceImpl.class);

    @Resource
    private TableHealthScoreRepository tableHealthScoreRepository;

    @Override
    public Double getCurrentTableHealthScore(int tid) {

        // 1.获取数据
        Double currentTableHealthScore = tableHealthScoreRepository.getCurrentTableHealthScore(tid);

        // 2.校验数据
        if (null == tableHealthScoreRepository || currentTableHealthScore < 0 || currentTableHealthScore > 100) {
            logger.error("健康分数据不合法");
            throw new ServiceException(ServiceExceptionEnum.HEALTH_SCORE_ILLEGAL_ERROR);
        }

        // 3.返回
        return currentTableHealthScore;
    }

    @Override
    public ScoreLineVO get7DaysTableHealthScoreLine(int tid) {

        // 1.获取数据
        ScoreLineVO line = tableHealthScoreRepository.query7DaysTableHealthScoreLine(tid);

        // 2.数据校验
        check7DaysHealthScoreLine(line);

        // 3.返回
        return line;
    }

}
