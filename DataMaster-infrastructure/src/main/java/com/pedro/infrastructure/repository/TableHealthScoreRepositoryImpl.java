package com.pedro.infrastructure.repository;

import com.pedro.common.config.Constants;
import com.pedro.domain.dbProcess.model.vo.TableHealthScoreVO;
import com.pedro.domain.form.model.vo.TableWeightVO;
import com.pedro.domain.score.model.vo.HidTidVO;
import com.pedro.domain.score.model.vo.ScoreLineVO;
import com.pedro.domain.score.repository.TableHealthScoreRepository;
import com.pedro.domain.support.number.NumberUtil;
import com.pedro.infrastructure.dao.TableHealthScoreDao;
import com.pedro.infrastructure.dao.TableInfoDao;
import com.pedro.infrastructure.po.ScoreLinePO;
import com.pedro.infrastructure.po.TableInfoPO;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TableHealthScoreRepositoryImpl implements TableHealthScoreRepository {

    @Resource
    private TableHealthScoreDao tableHealthScoreDao;

    @Resource
    private TableInfoDao tableInfoDao;

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

    @Override
    public List<TableWeightVO> queryAllTableWeight() {

        // 1.查询数据
        List<TableInfoPO> tableInfoPOList = tableInfoDao.queryAllTableInfo();

        // 2.数据转换
        List<TableWeightVO> tableWeightVOList = new ArrayList<>();
        for (TableInfoPO tableInfoPO : tableInfoPOList) {
            TableWeightVO oneRes = new TableWeightVO();
            oneRes.setTid(tableInfoPO.getTid());
            oneRes.setWeight(tableInfoPO.getTableWeight());
            tableWeightVOList.add(oneRes);
        }

        // 3.返回
        return tableWeightVOList;
    }

    @Override
    public List<HidTidVO> queryLastHidInPastOneHour() {
        return tableHealthScoreDao.queryLastHidInPastOneHour();
    }

    @Override
    public void deleteHealthScoreInPastOneHourExceptHid(HidTidVO hidTidVO) {
        tableHealthScoreDao.deleteHealthScoreInPastOneHourExceptHid(hidTidVO);
    }
}
