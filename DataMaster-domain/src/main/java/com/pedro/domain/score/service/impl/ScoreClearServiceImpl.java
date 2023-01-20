package com.pedro.domain.score.service.impl;

import com.pedro.domain.score.model.vo.HidTidVO;
import com.pedro.domain.score.model.vo.ThIdVO;
import com.pedro.domain.score.repository.TableHealthScoreRepository;
import com.pedro.domain.score.repository.TotalHealthScoreRepository;
import com.pedro.domain.score.service.ScoreClearService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ScoreClearServiceImpl implements ScoreClearService {

    @Resource
    private TableHealthScoreRepository tableHealthScoreRepository;

    @Resource
    private TotalHealthScoreRepository totalHealthScoreRepository;

    @Override
    public void deleteScoreInPastOneHour() {
        // TODO 事务

        // 1.清除单表健康分数据
        // 1.1 查询各表过去一小时内最后一条数据hid
        List<HidTidVO> lastHidTidVOList = tableHealthScoreRepository.queryLastHidInPastOneHour();
        // 1.2 删除各表过去一小时除最后一条数据外的报警数据
        for (HidTidVO hidTidVO : lastHidTidVOList) {
            tableHealthScoreRepository.deleteHealthScoreInPastOneHourExceptHid(hidTidVO);
        }

        // 2.清除整体健康分数据
        // 2.1 查询过去一小时内最后一条整体健康分的thid
        ThIdVO lastThId = totalHealthScoreRepository.queryLastThIdInPastOneHour();
        totalHealthScoreRepository.deleteTotalHealthScoreInPastOneHourExceptThId(lastThId);
    }
}
