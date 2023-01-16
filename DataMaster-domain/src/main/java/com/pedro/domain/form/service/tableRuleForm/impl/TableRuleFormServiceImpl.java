package com.pedro.domain.form.service.tableRuleForm.impl;

import com.pedro.common.enums.RuleTypeEnum;
import com.pedro.common.enums.ServiceExceptionEnum;
import com.pedro.common.exceptions.ServiceException;
import com.pedro.domain.form.model.res.TableDetailFormRes;
import com.pedro.domain.form.model.res.TableRuleFormRes;
import com.pedro.domain.form.model.vo.PieDataVO;
import com.pedro.domain.form.model.vo.RuleWeightVO;
import com.pedro.domain.form.model.vo.TableRuleFormVO;
import com.pedro.domain.form.repository.TableRuleFormRepository;
import com.pedro.domain.form.service.tableRuleForm.TableRuleFormService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TableRuleFormServiceImpl implements TableRuleFormService {

    private static final Logger logger = LoggerFactory.getLogger(TableRuleFormServiceImpl.class);

    @Resource
    private TableRuleFormRepository tableRuleFormRepository;

    @Override
    public List<TableRuleFormRes> loadTableRuleForm(int tid) {

        // 1.数据拉取
        List<TableRuleFormVO> tableRuleFormVOList = tableRuleFormRepository.loadTableRuleForm(tid);

        // 2.数据转换
        List<TableRuleFormRes> tableRuleFormResList = new ArrayList<>();
        for (TableRuleFormVO tableRuleFormVO : tableRuleFormVOList) {
            TableRuleFormRes oneRes = new TableRuleFormRes();
            oneRes.setRid(tableRuleFormVO.getRid());
            oneRes.setCid(tableRuleFormVO.getCid());
            // 2.1 获取列名
            String columnName = tableRuleFormRepository.queryColumnNameByCid(tableRuleFormVO.getCid());
            oneRes.setColumnName(columnName);
            oneRes.setRuleType(RuleTypeEnum.castTypeToString(tableRuleFormVO.getType()));
            // 2.2 处理约束值
            if (tableRuleFormVO.getType() == RuleTypeEnum.NULL_PERCENT_RULE.getType()) {
                // 2.2.1 空缺率
                oneRes.setRequiredValue("0");
                oneRes.setRequiredRange(tableRuleFormVO.getAppearRatio());
            } else if (tableRuleFormVO.getType() == RuleTypeEnum.VALUE_APPEAR_TIMES_RESTRICTION.getType()) {
                // 2.2.2 值出现次数
                oneRes.setRequiredValue(tableRuleFormVO.getValueAppear());
                oneRes.setRequiredRange(tableRuleFormVO.getAppearTimes());
            } else if (tableRuleFormVO.getType() == RuleTypeEnum.VALUE_APPEAR_RATIO_RESTRICTION.getType()) {
                // 2.2.3 值出现率
                oneRes.setRequiredValue(tableRuleFormVO.getValueAppear());
                oneRes.setRequiredRange(tableRuleFormVO.getAppearRatio());
            } else if (tableRuleFormVO.getType() == RuleTypeEnum.RANGE_APPEAR_TIMES_RESTRICTION.getType()) {
                // 2.2.4 数值范围出现次数
                oneRes.setRequiredValue(tableRuleFormVO.getValueRange());
                oneRes.setRequiredRange(tableRuleFormVO.getAppearTimes());
            } else if (tableRuleFormVO.getType() == RuleTypeEnum.RANGE_APPEAR_RATIO_RESTRICTION.getType()) {
                // 2.2.5 数值范围出现率
                oneRes.setRequiredValue(tableRuleFormVO.getValueRange());
                oneRes.setRequiredRange(tableRuleFormVO.getAppearRatio());
            } else {
                throw new ServiceException(ServiceExceptionEnum.STORAGE_RULE_TYPE_ERROR);
            }
            // 2.3 是否有报警
            boolean existAlarm = tableRuleFormRepository.queryIfExistAlarmByRid(tableRuleFormVO.getRid());
            oneRes.setWithAlarm(existAlarm ? "有" : "无");
            oneRes.setWeight(tableRuleFormVO.getRuleWeight());

            tableRuleFormResList.add(oneRes);
        }

        // 3.数据返回
        return tableRuleFormResList;
    }

    @Override
    public List<PieDataVO> loadRuleTypePie(int tid) {

        // 1.数据拉取
        List<TableRuleFormVO> tableRuleFormVOList = tableRuleFormRepository.loadTableRuleForm(tid);

        // 2.数据转换
        List<PieDataVO> pieDataVOList = new ArrayList<>();
        // 2.1 构造对应VO,存入map中等待处理
        Map<Integer, PieDataVO> pieDataVOMap = new HashMap<>();
        // 2.1.1 空缺率
        PieDataVO nullPercentRuleVO = new PieDataVO(RuleTypeEnum.NULL_PERCENT_RULE.getMsg(), 0);
        pieDataVOMap.put(RuleTypeEnum.NULL_PERCENT_RULE.getType(), nullPercentRuleVO);
        pieDataVOList.add(nullPercentRuleVO);
        // 2.1.2 值出现次数
        PieDataVO valueAppearTimesVO = new PieDataVO(RuleTypeEnum.VALUE_APPEAR_TIMES_RESTRICTION.getMsg(), 0);
        pieDataVOMap.put(RuleTypeEnum.VALUE_APPEAR_TIMES_RESTRICTION.getType(), valueAppearTimesVO);
        pieDataVOList.add(valueAppearTimesVO);
        // 2.1.3 值出现率
        PieDataVO valueAppearRatioVO = new PieDataVO(RuleTypeEnum.VALUE_APPEAR_RATIO_RESTRICTION.getMsg(), 0);
        pieDataVOMap.put(RuleTypeEnum.VALUE_APPEAR_RATIO_RESTRICTION.getType(), valueAppearRatioVO);
        pieDataVOList.add(valueAppearRatioVO);
        // 2.1.4 数值范围出现次数
        PieDataVO rangeAppearTimesVO = new PieDataVO(RuleTypeEnum.RANGE_APPEAR_TIMES_RESTRICTION.getMsg(), 0);
        pieDataVOMap.put(RuleTypeEnum.RANGE_APPEAR_TIMES_RESTRICTION.getType(), rangeAppearTimesVO);
        pieDataVOList.add(rangeAppearTimesVO);
        // 2.1.5 数值范围出现率
        PieDataVO rangeAppearRatioVO = new PieDataVO(RuleTypeEnum.RANGE_APPEAR_RATIO_RESTRICTION.getMsg(), 0);
        pieDataVOMap.put(RuleTypeEnum.RANGE_APPEAR_RATIO_RESTRICTION.getType(), rangeAppearRatioVO);
        pieDataVOList.add(rangeAppearRatioVO);
        // 2.2 构造数据
        for (TableRuleFormVO tableRuleFormVO : tableRuleFormVOList) {
            pieDataVOMap.get(tableRuleFormVO.getType()).incValue();
        }

        // 3.返回
        return pieDataVOList;
    }

    @Override
    public void editRuleWeight(int rid, int weight) {

        // 1.构造VO
        RuleWeightVO ruleWeightVO = new RuleWeightVO();
        ruleWeightVO.setRid(rid);
        ruleWeightVO.setWeight(weight);

        // 2.执行编辑
        int editCount = tableRuleFormRepository.editRuleWeight(ruleWeightVO);

        // 3.编辑数量异常
        if (editCount != 1) {
            logger.error("编辑约束权重时出现异常");
            throw new ServiceException(ServiceExceptionEnum.EDIT_RULE_WEIGHT_ERROR);
        }
    }

    @Override
    public void deleteRule(int rid) {

        // 1.构造待排除集合
        List<Integer> ridList = new ArrayList<>();
        ridList.add(rid);

        // 2.执行删除
        // TODO 事务
        // 2.1 从alarm表中删除
        tableRuleFormRepository.deleteAlarmByRid(ridList);
        // 2.2从rule表中删除
        int deleteCount = tableRuleFormRepository.deleteRule(ridList);
        if (deleteCount != ridList.size()) {
            throw new ServiceException(ServiceExceptionEnum.DELETE_RULE_ERROR);
        }
    }

    @Override
    public void batchDeleteRule(List<Integer> ridList) {

        // 1. 从alarm表中删除
        tableRuleFormRepository.deleteAlarmByRid(ridList);

        // 2. 从rule表中删除
        int deleteCount = tableRuleFormRepository.deleteRule(ridList);
        if (deleteCount != ridList.size()) {
            throw new ServiceException(ServiceExceptionEnum.DELETE_RULE_ERROR);
        }
    }
}
