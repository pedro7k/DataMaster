package com.pedro.interfaces.controller;

import com.pedro.common.res.CommonResult;
import com.pedro.domain.form.model.res.TableDetailFormRes;
import com.pedro.domain.form.model.res.TableManageFormRes;
import com.pedro.domain.form.service.tableDetailForm.TableDetailFormService;
import com.pedro.domain.score.model.vo.ScoreLineVO;
import com.pedro.domain.score.service.TableHealthScoreService;
import com.pedro.infrastructure.dao.TableInfoDao;
import com.pedro.infrastructure.po.TableInfoPO;
import com.pedro.interfaces.res.CommonFormDataRes;
import com.pedro.interfaces.res.CurrentTableHealthScoreRes;
import com.pedro.interfaces.res.CurrentTotalHealthScoreRes;
import com.pedro.interfaces.res.TableNameRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class TableDetailController {

    private static final Logger logger = LoggerFactory.getLogger(TableDetailController.class);

    @Resource
    private TableInfoDao tableInfoDao;

    @Resource
    private TableHealthScoreService tableHealthScoreService;

    @Resource
    private TableDetailFormService tableDetailFormService;

    /**
     * 跳转到数据表详情页
     */
    @GetMapping("/jumpToDetailPage")
    public ModelAndView jumpToDetailPage(int tid) {
        logger.info("jumpToDetailPage,tid={}", tid);
        ModelAndView mv = new ModelAndView("detail.html");
        mv.addObject("tid", tid);
        return mv;
    }

    /**
     * 跳转到报警管理页
     */
    @GetMapping("/jumpToAlarmPage")
    public ModelAndView jumpToAlarmPage(int tid) {
        ModelAndView mv = new ModelAndView("alarm.html");
        mv.addObject("tid", tid);
        return mv;
    }

    /**
     * 获取当前tid对应的表名
     */
    @PostMapping("/getTableName")
    public CommonResult getTableName(int tid) {

        TableNameRes tableNameRes = new TableNameRes();
        TableInfoPO tableInfoPO = tableInfoDao.queryTableInfoByTid(tid);
        String tableName = tableInfoPO.getName();
        tableNameRes.setTableName(tableName);

        return CommonResult.success(tableNameRes);
    }

    /**
     * 获取表当前健康分
     */
    @GetMapping("/getCurrentTableHealthScore")
    public CommonResult getCurrentTableHealthScore(int tid) {

        // 1.获得数据
        Double currentTotalHealthScore = tableHealthScoreService.getCurrentTableHealthScore(tid);

        // 2.返回
        return CommonResult.success(new CurrentTableHealthScoreRes(currentTotalHealthScore));
    }

    /**
     * 获取过去七日整体健康分变化趋势
     */
    @GetMapping("/get7DaysTableHealthScoreLine")
    public CommonResult get7DaysTableHealthScoreLine(int tid) {

        // 1.获得数据
        ScoreLineVO line = tableHealthScoreService.get7DaysTableHealthScoreLine(tid);

        // 2.返回
        return CommonResult.success(line);
    }

    /**
     * 拉取表单详情页表单数据
     */
    @GetMapping("/loadTableDetailForm")
    public CommonResult loadTableDetailForm(int tid) {

        // 1.拉取表单数据
        List<TableDetailFormRes> tableDetailFormResList = tableDetailFormService.loadTableDetailForm(tid);

        // 2.构造返回结果
        CommonFormDataRes<TableDetailFormRes> tableDetailFormCommonResult = new CommonFormDataRes<>(tableDetailFormResList, tableDetailFormResList.size());

        // 3.返回
        return CommonResult.success(tableDetailFormCommonResult);
    }
}
