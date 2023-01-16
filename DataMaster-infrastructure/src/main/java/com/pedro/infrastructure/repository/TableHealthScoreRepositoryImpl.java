package com.pedro.infrastructure.repository;

import com.pedro.domain.score.model.vo.ScoreLineVO;
import com.pedro.domain.score.repository.TableHealthScoreRepository;
import com.pedro.infrastructure.dao.TableHealthScoreDao;
import com.pedro.infrastructure.po.ScoreLinePO;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;

@Repository
public class TableHealthScoreRepositoryImpl implements TableHealthScoreRepository {

    /**
     * 日期转换器，转为 月.日
     */
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("M.d");

    @Resource
    private TableHealthScoreDao tableHealthScoreDao;

    @Override
    public Double getCurrentTableHealthScore(int tid) {
        return tableHealthScoreDao.queryCurrentTableHealthScoreByTid(tid);
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
}
