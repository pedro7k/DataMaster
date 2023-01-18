package com.pedro.interfaces.controller;

import com.google.common.base.Splitter;
import com.pedro.common.enums.ServiceExceptionEnum;
import com.pedro.common.enums.UserRoleEnum;
import com.pedro.common.res.CommonResult;
import com.pedro.domain.form.model.res.TableAlarmFormRes;
import com.pedro.domain.form.model.res.TableRuleFormRes;
import com.pedro.domain.form.model.vo.PieDataVO;
import com.pedro.domain.form.service.tableAlarmForm.TableAlarmFormService;
import com.pedro.domain.user.model.vo.UserVO;
import com.pedro.interfaces.res.CommonFormDataRes;
import com.pedro.interfaces.res.PieDataRes;
import com.pedro.interfaces.role.ShiroUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class TableAlarmController {

    private static final Logger logger = LoggerFactory.getLogger(TableAlarmController.class);

    @Resource
    private TableAlarmFormService tableAlarmFormService;

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
     * 获取报警饼图数据
     */
    @GetMapping("getAlarmPieData")
    public CommonResult getAlarmPieData(int tid) {

        // TODO crud数据变化时，同一页面上的饼图不能做到自动刷新

        // 1.拉取饼图数据
        List<PieDataVO> pieDataVOList = tableAlarmFormService.loadAlarmStatePie(tid);

        // 2.转变格式
        PieDataRes pieDataRes = new PieDataRes(pieDataVOList);

        // 3.返回
        return CommonResult.success(pieDataRes);
    }

    /**
     * 拉取报警管理页表单数据
     */
    @GetMapping("/loadTableAlarmForm")
    public CommonResult loadTableAlarmForm(int tid) {

        // 1.拉取表单数据
        List<TableAlarmFormRes> tableAlarmFormResList = tableAlarmFormService.loadTableAlarmForm(tid);

        // 2.构造返回结果
        CommonFormDataRes<TableAlarmFormRes> tableAlarmFormCommonResult = new CommonFormDataRes<>(tableAlarmFormResList, tableAlarmFormResList.size());

        // 3.返回
        return CommonResult.success(tableAlarmFormCommonResult);
    }

    /**
     * 快速编辑约束权重
     */
    @PostMapping("/quickEditAlarmState")
    public CommonResult quickEditAlarmState(@RequestParam("aid") int aid, @RequestParam("state") String state) {

        // 1.获取当前权限
        UserVO currentUser = ShiroUtil.getCurrentUser();
        int role = currentUser.getRole();

        // 2.权限不足
        if (role > UserRoleEnum.ADMIN.getLevel()) {
            logger.info("[roleDenied]权限不足，user={},time={}", currentUser.getUsername(), new Date());
            return CommonResult.error(ServiceExceptionEnum.ROLE_DENIED);
        }

        // 3.编辑
        tableAlarmFormService.editAlarmState(aid, state);

        // 4.返回
        return CommonResult.success(null, "修改成功！");
    }


    /**
     * 删除单个报警
     */
    @PostMapping("/deleteAlarm")
    public CommonResult deleteAlarm(@RequestParam("aid") int aid) {

        // 1.获取当前权限
        UserVO currentUser = ShiroUtil.getCurrentUser();
        int role = currentUser.getRole();

        // 2.权限不足
        if (role > UserRoleEnum.ADMIN.getLevel()) {
            logger.info("[roleDenied]权限不足，user={},time={}", currentUser.getUsername(), new Date());
            return CommonResult.error(ServiceExceptionEnum.ROLE_DENIED);
        }

        // 3.执行删除
        tableAlarmFormService.deleteAlarm(aid);

        // 4.返回
        return CommonResult.success(null, "删除成功");
    }

    /**
     * 批量删除报警
     */
    @PostMapping("/batchDeleteAlarm")
    public CommonResult batchDeleteAlarm(@RequestParam("ids") String aids){

        // 1.获取当前权限
        UserVO currentUser = ShiroUtil.getCurrentUser();
        int role = currentUser.getRole();

        // 2.权限不足
        if (role > UserRoleEnum.ADMIN.getLevel()) {
            logger.info("[roleDenied]权限不足，user={},time={}", currentUser.getUsername(), new Date());
            return CommonResult.error(ServiceExceptionEnum.ROLE_DENIED);
        }

        // 3.获取id列表
        List<Integer> aidList = Splitter.on(",").trimResults().omitEmptyStrings().splitToList(aids)
                .stream().map(Integer::parseInt).collect(Collectors.toList());

        // 4.执行删除
        tableAlarmFormService.batchDeleteAlarm(aidList);

        // 5.返回
        return CommonResult.success(null, "删除成功");
    }
}
