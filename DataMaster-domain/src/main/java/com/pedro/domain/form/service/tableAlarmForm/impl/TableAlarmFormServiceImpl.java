package com.pedro.domain.form.service.tableAlarmForm.impl;

import com.pedro.common.enums.AlarmStateEnum;
import com.pedro.common.enums.RuleTypeEnum;
import com.pedro.common.enums.ServiceExceptionEnum;
import com.pedro.common.exceptions.ServiceException;
import com.pedro.domain.dbProcess.model.vo.MuchDeleteVO;
import com.pedro.domain.form.model.res.TableAlarmFormRes;
import com.pedro.domain.form.model.vo.*;
import com.pedro.domain.form.repository.TableAlarmFormRepository;
import com.pedro.domain.form.repository.TableRuleFormRepository;
import com.pedro.domain.form.service.tableAlarmForm.TableAlarmFormService;
import com.pedro.domain.form.service.tableRuleForm.impl.TableRuleFormServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class TableAlarmFormServiceImpl implements TableAlarmFormService {

    private static final Logger logger = LoggerFactory.getLogger(TableAlarmFormServiceImpl.class);

    @Resource
    private TableAlarmFormRepository tableAlarmFormRepository;

    @Resource
    private TableRuleFormRepository tableRuleFormRepository;

    /**
     * 日期转换器，转为 年-月-日 时:分:秒
     */
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public List<TableAlarmFormRes> loadTableAlarmForm(int tid) {

        // 1.数据拉取
        List<TableAlarmFormVO> tableAlarmFormVOList = tableAlarmFormRepository.loadTableAlarmForm(tid);

        // 2.数据转换和填充
        List<TableAlarmFormRes> tableAlarmFormResList = new ArrayList<>();
        for (TableAlarmFormVO tableAlarmFormVO : tableAlarmFormVOList) {
            TableAlarmFormRes oneRes = new TableAlarmFormRes();
            oneRes.setAid(tableAlarmFormVO.getAid());
            oneRes.setRid(tableAlarmFormVO.getRid());
            // 通过rid查询约束基础信息
            TableRuleBaseVO tableRuleBaseVO = tableAlarmFormRepository.queryRuleByRid(oneRes.getRid());
            // 查询列名
            String columnName = tableRuleFormRepository.queryColumnNameByCid(tableRuleBaseVO.getCid());
            oneRes.setColumnName(columnName);
            oneRes.setAlarmType(RuleTypeEnum.castTypeToString(tableRuleBaseVO.getType()));
            oneRes.setAlarmValue(tableAlarmFormVO.getValue());
            oneRes.setTime(simpleDateFormat.format(tableAlarmFormVO.getTime()));
            oneRes.setRuleWeight(tableRuleBaseVO.getRuleWeight());
            oneRes.setState(AlarmStateEnum.castTypeToString(tableAlarmFormVO.getState()));
            tableAlarmFormResList.add(oneRes);
        }

        // 3.短时间大量删除变更记录检查
        MuchDeleteVO muchDeleteRecord = tableAlarmFormRepository.queryMuchDeleteRecord(tid);
        if (muchDeleteRecord != null) {
            TableAlarmFormRes oneRes = new TableAlarmFormRes();
            oneRes.setAid(0);
            oneRes.setAlarmType("短时大量删除");
            oneRes.setTime(simpleDateFormat.format(muchDeleteRecord.getTime()));
            oneRes.setState(AlarmStateEnum.castTypeToString(muchDeleteRecord.getState()));
            tableAlarmFormResList.add(0, oneRes);
        }

        // 4.数据返回
        return tableAlarmFormResList;
    }

    @Override
    public List<PieDataVO> loadAlarmStatePie(int tid) {

        // 1.数据拉取
        List<TableAlarmFormVO> tableAlarmFormVOList = tableAlarmFormRepository.loadTableAlarmForm(tid);

        // 2.数据转换
        List<PieDataVO> pieDataVOList = new ArrayList<>();
        // 2.1 构造对应VO,存入map中等待处理
        Map<Integer, PieDataVO> pieDataVOMap = new HashMap<>();
        // 2.1.1 等待处理
        PieDataVO waitingProcessVO = new PieDataVO(AlarmStateEnum.WAITING_PROCESS.getMsg(), 0);
        pieDataVOMap.put(AlarmStateEnum.WAITING_PROCESS.getState(), waitingProcessVO);
        pieDataVOList.add(waitingProcessVO);
        // 2.1.2 正在处理
        PieDataVO underProcessVO = new PieDataVO(AlarmStateEnum.UNDER_PROCESS.getMsg(), 0);
        pieDataVOMap.put(AlarmStateEnum.UNDER_PROCESS.getState(), underProcessVO);
        pieDataVOList.add(underProcessVO);
        // 2.1.1 等待处理
        PieDataVO finishProcessVO = new PieDataVO(AlarmStateEnum.FINISH_PROCESS.getMsg(), 0);
        pieDataVOMap.put(AlarmStateEnum.FINISH_PROCESS.getState(), finishProcessVO);
        pieDataVOList.add(finishProcessVO);
        // 2.2 构造数据
        for (TableAlarmFormVO tableAlarmFormVO : tableAlarmFormVOList) {
            pieDataVOMap.get(tableAlarmFormVO.getState()).incValue();
        }

        // 3.返回
        return pieDataVOList;
    }

    @Override
    public void editAlarmState(int tid, int aid, String state) {

        // 1.处理aid=0，即短时大量删除的情况
        if (aid == 0) {
            MuchDeleteVO muchDeleteVO = new MuchDeleteVO();
            muchDeleteVO.setTid(tid);
            muchDeleteVO.setState(AlarmStateEnum.castTypeToInt(state));
            tableAlarmFormRepository.updateMuchDeleteRecordState(muchDeleteVO);
            return;
        }

        // 2.构造VO
        AlarmStateVO alarmStateVO = new AlarmStateVO();
        alarmStateVO.setAid(aid);
        alarmStateVO.setState(AlarmStateEnum.castTypeToInt(state));

        // 2.执行编辑
        int editCount = tableAlarmFormRepository.editAlarmState(alarmStateVO);

        // 3.编辑数量异常
        if (editCount != 1) {
            logger.error("编辑报警状态时出现异常");
            throw new ServiceException(ServiceExceptionEnum.EDIT_ALARM_STATE_ERROR);
        }

    }

    @Override
    public void deleteAlarm(int tid, int aid) {

        // 1.处理aid=0，即大量删除变更
        if (aid == 0) {
            tableAlarmFormRepository.deleteMuchDeleteRecordByTid(tid);
            return;
        }

        // 2.构造待删除集合
        List<Integer> aidList = new ArrayList<>(1);
        aidList.add(aid);

        // 3.执行删除
        int deleteCount = tableAlarmFormRepository.deleteAlarmByAid(aidList);
        if (deleteCount != aidList.size()) {
            logger.error("删除报警条目时出现异常");
            throw new ServiceException(ServiceExceptionEnum.DELETE_ALARM_ERROR);
        }

    }

    @Override
    public void batchDeleteAlarm(int tid, List<Integer> aidList) {

        // 1.遍历aidList，检查是否有aid==0
        boolean muchDeleteFlag = false;
        for (Integer aid : aidList) {
            if (aid == 0) {
                tableAlarmFormRepository.deleteMuchDeleteRecordByTid(tid);
                muchDeleteFlag = true;
            }
        }
        if (muchDeleteFlag) {
            aidList.remove((Integer) 0);
        }

        // 2.执行删除
        int deleteCount = tableAlarmFormRepository.deleteAlarmByAid(aidList);

        if (deleteCount != aidList.size()) {
            logger.error("删除报警条目时出现异常");
            throw new ServiceException(ServiceExceptionEnum.DELETE_ALARM_ERROR);
        }
    }
}
