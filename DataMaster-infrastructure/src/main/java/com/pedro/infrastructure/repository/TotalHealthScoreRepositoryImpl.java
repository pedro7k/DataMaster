package com.pedro.infrastructure.repository;

import com.pedro.common.config.Constants;
import com.pedro.domain.score.model.vo.ScoreLineVO;
import com.pedro.domain.score.repository.TotalHealthScoreRepository;
import com.pedro.domain.support.number.NumberUtil;
import com.pedro.infrastructure.dao.TotalHealthScoreDao;
import com.pedro.infrastructure.po.ScoreLinePO;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class TotalHealthScoreRepositoryImpl implements TotalHealthScoreRepository {

    @Resource
    private TotalHealthScoreDao totalHealthScoreDao;

    /**
     * 日期转换器，转为 月.日
     */
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("M.d");

    @Override
    public Double queryCurrentTotalHealthScore() {

        // 1.获取分数
        Double score = totalHealthScoreDao.queryCurrentTotalHealthScore();

        // 2.转换结果并返回
        return NumberUtil.reserveDoubleScale(score, Constants.HEALTH_SCORE_SCALE);
    }

    @Override
    public ScoreLineVO query7DaysTotalHealthScoreLine() {

        // 1.获取结果
        List<ScoreLinePO> scoreLinePOS = totalHealthScoreDao.query7DaysTotalHealthScoreLine();

        // 2.格式处理
        ScoreLineVO line = scoreLineFormProcess(scoreLinePOS);

        // 3.返回结果
        return line;
    }

    /**
     * ScoreLinePO格式处理
     *
     * @param scoreLinePOS
     * @return
     */
    public static ScoreLineVO scoreLineFormProcess(List<ScoreLinePO> scoreLinePOS) {

        // 2.结合sql，为保持时间顺序，应将结果倒置
        Collections.reverse(scoreLinePOS);

        // 3.格式转换，转为分数和日期各列为list
        ScoreLineVO line = new ScoreLineVO();
        for (ScoreLinePO scoreLinePO : scoreLinePOS) {
            // 转换分数
            double score = NumberUtil.reserveDoubleScale(scoreLinePO.getScore(), Constants.HEALTH_SCORE_SCALE);
            line.getScore().add(score);
            line.getDate().add(simpleDateFormat.format(scoreLinePO.getTime()));
        }

        return line;
    }
}
