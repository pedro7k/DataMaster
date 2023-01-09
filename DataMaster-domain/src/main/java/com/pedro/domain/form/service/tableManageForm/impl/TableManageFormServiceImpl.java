package com.pedro.domain.form.service.tableManageForm.impl;

import com.pedro.common.enums.ServiceExceptionEnum;
import com.pedro.common.exceptions.ServiceException;
import com.pedro.domain.form.model.res.TableManageFormRes;
import com.pedro.domain.form.model.vo.TableBaseInfoVO;
import com.pedro.domain.form.model.vo.TableWeightVO;
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

    @Override
    public void exceptTable(int tid) {

        // 1.构造待排除集合
        List<Integer> tidList = new ArrayList<>();
        tidList.add(tid);

        // 2.获取表名，存入except表中
        String tableName = tableManageFormRepository.queryTableNameByTid(tid);
        boolean isSuccess = tableManageFormRepository.insertExceptTable(tableName);
        if (!isSuccess) {
            logger.error("新增except表失败");
            throw new ServiceException(ServiceExceptionEnum.INSERT_EXCEPT_TABLE_ERROR);
        }

        // 3.执行排除 alarm表->rule表->detail表->score表->info表
        tableManageFormRepository.exceptTableByTid(tidList);

    }

    @Override
    public void batchExceptTable(List<Integer> tidList) {

        // 1.获取表名，存入except表中
        for (Integer tid : tidList) {
            String tableName = tableManageFormRepository.queryTableNameByTid(tid);
            boolean isSuccess = tableManageFormRepository.insertExceptTable(tableName);
            if (!isSuccess) {
                logger.error("新增except表失败");
                throw new ServiceException(ServiceExceptionEnum.INSERT_EXCEPT_TABLE_ERROR);
            }
        }

        // 2.批量执行排除
        tableManageFormRepository.exceptTableByTid(tidList);
    }

    @Override
    public void editTableWeight(int tid, int weight) {

        // 1.构造VO
        TableWeightVO tableWeightVO = new TableWeightVO();
        tableWeightVO.setTid(tid);
        tableWeightVO.setWeight(weight);

        // 2.执行编辑
        int editCount = tableManageFormRepository.editTableWeight(tableWeightVO);

        // 3.编辑数量异常
        if(editCount != 1){
            logger.error("编辑表单权重时出现异常");
            throw new ServiceException(ServiceExceptionEnum.EDIT_TABLE_WEIGHT_ERROR);
        }
    }
}
