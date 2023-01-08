package com.pedro.domain.form.service.tableManageForm.impl;

import com.pedro.common.enums.ServiceExceptionEnum;
import com.pedro.common.exceptions.ServiceException;
import com.pedro.domain.form.model.res.TableManageFormRes;
import com.pedro.domain.form.model.vo.TableBaseInfoVO;
import com.pedro.domain.form.repository.TableManageFormRepository;
import com.pedro.domain.form.service.tableManageForm.TableManageFormService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class TableManageFormServiceImpl implements TableManageFormService {

    private static final Logger logger = LoggerFactory.getLogger(TableManageFormServiceImpl.class);

    @Resource
    private TableManageFormRepository tableManageFormRepository;

    @Override
    public List<TableManageFormRes> loadTableManageForm() {

        // 1.构造结果
        List<TableManageFormRes> res = new ArrayList<>();

        // 2.获取表单基本信息
        List<TableBaseInfoVO> tableBaseInfoVOList = tableManageFormRepository.queryTableInfo();
        for (TableBaseInfoVO tableBaseInfoVO : tableBaseInfoVOList) {
            TableManageFormRes oneRes = new TableManageFormRes();
            oneRes.setTid(tableBaseInfoVO.getTid());
            oneRes.setName(tableBaseInfoVO.getName());
            oneRes.setWeight(tableBaseInfoVO.getWeight());
            res.add(oneRes);
        }

        // 3.遍历获取表健康分和报警次数
        for (TableManageFormRes oneRes : res) {
            // 3.1 获取表健康分
            Double score = tableManageFormRepository.queryHealthScoreByTid(oneRes.getTid());
            if (null == score || score < 0 || score > 100) {
                logger.error("健康分数据不合法");
                throw new ServiceException(ServiceExceptionEnum.HEALTH_SCORE_ILLEGAL_ERROR);
            }
            oneRes.setScore(score);

            // 3.2 获取报警次数
            int alarmTimes = tableManageFormRepository.queryAlarmTimesByTid(oneRes.getTid());
            oneRes.setAlarmTimes(alarmTimes);
        }

        // 4.返回
        return res;
    }
}
