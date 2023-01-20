package com.pedro.infrastructure.repository;

import com.pedro.common.config.Constants;
import com.pedro.domain.dbProcess.model.vo.TableHealthScoreVO;
import com.pedro.domain.score.model.vo.ScoreLineVO;
import com.pedro.domain.score.repository.TableHealthScoreRepository;
import com.pedro.domain.support.number.NumberUtil;
import com.pedro.infrastructure.dao.TableHealthScoreDao;
import com.pedro.infrastructure.po.ScoreLinePO;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;

@Repository
public class TableHealthScoreRepositoryImpl implements TableHealthScoreRepository {

    @Resource
    private TableHealthScoreDao tableHealthScoreDao;

    @Override
    public Double getCurrentTableHealthScore(int tid) {

        // 1.获取分数
        Double score = tableHealthScoreDao.queryCurrentTableHealthScoreByTid(tid);

        // 2.保留小数并返回
        return NumberUtil.reserveDoubleScale(score, Constants.HEALTH_SCORE_SCALE);
    }

    @Override
    public ScoreLineVO query7DaysTableHealthScoreLine(int tid) {

        // 1.获取结果
        List<ScoreLinePO> scoreLinePOS = tableHealthScoreDao.query7DaysTableHealthScoreLine(tid);

        // 2.格式处理
        ScoreLineVO line = TotalHealthScoreRepositoryImpl.scoreLineFormProcess(scoreLinePOS);

        // 4.返回结果
        return line;
    }

    @Override
    public void insertTableHealthScore(int tid, Double score) {

        // 1.构造数据
        TableHealthScoreVO tableHealthScoreVO = new TableHealthScoreVO();
        tableHealthScoreVO.setTid(tid);
        // 保留小数
        score = NumberUtil.reserveDoubleScale(score, Constants.HEALTH_SCORE_SCALE);
        tableHealthScoreVO.setScore(score);

        // 2.插入
        tableHealthScoreDao.insertTableHealthScore(tableHealthScoreVO);
    }
}
