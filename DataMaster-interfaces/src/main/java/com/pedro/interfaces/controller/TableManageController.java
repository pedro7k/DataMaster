package com.pedro.interfaces.controller;

import com.google.common.base.Splitter;
import com.pedro.common.enums.ServiceExceptionEnum;
import com.pedro.common.enums.UserRoleEnum;
import com.pedro.common.res.CommonResult;
import com.pedro.domain.dbProcess.model.req.TableCreationReq;
import com.pedro.domain.form.model.res.TableManageFormRes;
import com.pedro.domain.form.service.tableManageForm.TableManageFormService;
import com.pedro.domain.score.service.TotalHealthScoreService;
import com.pedro.domain.user.model.vo.UserVO;
import com.pedro.interfaces.res.CommonFormDataRes;
import com.pedro.interfaces.res.CurrentTotalHealthScoreRes;
import com.pedro.domain.score.model.vo.ScoreLineVO;
import com.pedro.interfaces.res.UsernameRes;
import com.pedro.interfaces.role.ShiroUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 库表管理页Controller
 */
@RestController
public class TableManageController {

    private static final Logger logger = LoggerFactory.getLogger(TableManageController.class);

    @Resource
    TotalHealthScoreService totalHealthScoreService;

    @Resource
    TableManageFormService tableManageFormService;

    /**
     * 跳转到库表管理页
     */
    @GetMapping("/jumpToTableManage")
    public ModelAndView jumpToTableManage() {
        ModelAndView mv = new ModelAndView("tableManage.html");
        return mv;
    }

    /**
     * 获取当前用户
     */
    @GetMapping("/getCurrentUser")
    public CommonResult getCurrentUser() {
        logger.info("getCurrentUser");

        // 1.获取当前user
        UserVO currentUser = ShiroUtil.getCurrentUser();

        // 2.在用户名后标注权限等级
        String username = currentUser.getUsername();
        username = username + " " + UserRoleEnum.castRoleToString(currentUser.getRole());

        // 3.保证安全，构造一个仅包含username的对象
        UsernameRes usernameRes = new UsernameRes();
        usernameRes.setUsername(username);

        // 4.返回
        return CommonResult.success(usernameRes);
    }

    /**
     * 注销
     */
    @GetMapping("/logout")
    public CommonResult logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();

        return CommonResult.success(null);
    }

    /**
     * 获取当前整体健康分
     */
    @GetMapping("/getCurrentTotalHealthScore")
    public CommonResult getCurrentTotalHealthScore() {

        // 1.获得数据
        Double currentTotalHealthScore = totalHealthScoreService.getCurrentTotalHealthScore();

        // 2.返回
        return CommonResult.success(new CurrentTotalHealthScoreRes(currentTotalHealthScore));
    }

    /**
     * 获取过去七日整体健康分变化趋势
     */
    @GetMapping("/get7DaysTotalHealthScoreLine")
    public CommonResult get7DaysTotalHealthScoreLine() {

        // 1.获得数据
        ScoreLineVO line = totalHealthScoreService.get7DaysTotalHealthScoreLine();

        // 2.返回
        return CommonResult.success(line);
    }

    /**
     * 拉取表单数据
     */
    @GetMapping("/loadTableManageForm")
    public CommonResult loadTableManageForm() {

        // 1.拉取表单数据
        List<TableManageFormRes> tableManageFormResList = tableManageFormService.loadTableManageForm();

        // 2.构造返回结果
        CommonFormDataRes<TableManageFormRes> tableManageVOCommonResult = new CommonFormDataRes<>(tableManageFormResList, tableManageFormResList.size());

        // 3.返回
        return CommonResult.success(tableManageVOCommonResult);
    }

    /**
     * 排除单个表单
     */
    @PostMapping("/exceptTable")
    public CommonResult exceptTable(@RequestParam("tid") int tid) {

        // 1.获取当前权限
        UserVO currentUser = ShiroUtil.getCurrentUser();
        int role = currentUser.getRole();

        // 2.权限不足
        if (role > UserRoleEnum.ADMIN.getLevel()) {
            logger.info("[roleDenied]权限不足，user={},time={}", currentUser.getUsername(), new Date());
            return CommonResult.error(ServiceExceptionEnum.ROLE_DENIED);
        }

        // 3.执行排除
        tableManageFormService.exceptTable(tid);

        // 4.返回
        return CommonResult.success(null, "删除成功");
    }

    /**
     * 批量排除表单
     */
    @PostMapping("/batchExceptTable")
    public CommonResult batchExceptTable(@RequestParam("ids") String tids) {

        // 1.获取当前权限
        UserVO currentUser = ShiroUtil.getCurrentUser();
        int role = currentUser.getRole();

        // 2.权限不足
        if (role > UserRoleEnum.ADMIN.getLevel()) {
            logger.info("[roleDenied]权限不足，user={},time={}", currentUser.getUsername(), new Date());
            return CommonResult.error(ServiceExceptionEnum.ROLE_DENIED);
        }

        // 3.获取tid列表
        List<Integer> tidList = Splitter.on(",").trimResults().omitEmptyStrings().splitToList(tids)
                .stream().map(Integer::parseInt).collect(Collectors.toList());

        // 4.批量删除
        tableManageFormService.batchExceptTable(tidList);

        // 5.返回
        return CommonResult.success(null, "删除成功");
    }

    /**
     * 快速编辑表单权重
     */
    @PostMapping("/quickEditTableWeight")
    public CommonResult quickEditTableWeight(@RequestParam("tid") int tid, @RequestParam("weight") int weight) {

        // 1.获取当前权限
        UserVO currentUser = ShiroUtil.getCurrentUser();
        int role = currentUser.getRole();

        // 2.权限不足
        if (role > UserRoleEnum.ADMIN.getLevel()) {
            logger.info("[roleDenied]权限不足，user={},time={}", currentUser.getUsername(), new Date());
            return CommonResult.error(ServiceExceptionEnum.ROLE_DENIED);
        }

        // 3.编辑
        tableManageFormService.editTableWeight(tid, weight);

        // 4.返回
        return CommonResult.success(null, "修改成功！");
    }

    /**
     * 可视化创建表
     */
    @PostMapping("/createTable")
    public CommonResult createTable(@RequestBody TableCreationReq tableCreationReq){

        // 1.获取当前权限
        UserVO currentUser = ShiroUtil.getCurrentUser();
        int role = currentUser.getRole();

        // 2.权限不足
        if (role > UserRoleEnum.ADMIN.getLevel()) {
            logger.info("[roleDenied]权限不足，user={},time={}", currentUser.getUsername(), new Date());
            return CommonResult.error(ServiceExceptionEnum.ROLE_DENIED);
        }

        // TODO 3.执行表创建
        System.out.println(tableCreationReq);

        // 4.返回
        return CommonResult.success(null,"创建成功！刷新表单可见");
    }
}
