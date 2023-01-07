package com.pedro.infrastructure.repository;

import com.pedro.domain.score.repository.TotalHealthScoreRepository;
import com.pedro.infrastructure.dao.TotalHealthScoreDao;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

@Repository
public class TotalHealthScoreRepositoryImpl implements TotalHealthScoreRepository {

    @Resource
    private TotalHealthScoreDao totalHealthScoreDao;

    @Override
    public Double queryCurrentTotalHealthScore() {
        return totalHealthScoreDao.queryCurrentTotalHealthScore();
    }

    @Override
    public List<Double> query7DaysTotalHealthScoreLine() {

        // 1.获取结果
        List<Double> line = totalHealthScoreDao.query7DaysTotalHealthScoreLine();

        // 2.结合sql，为保持时间顺序，因将结果倒置
        Collections.reverse(line);

        // 3.返回结果
        return line;
    }
}
