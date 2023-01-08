package com.pedro.interfaces.controller;

import com.pedro.common.res.CommonResult;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

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
    public CommonResult getCurrentUser(){
        logger.info("getCurrentUser");

        // 1.获取当前user
        UserVO currentUser = ShiroUtil.getCurrentUser();

        // 2.保证安全，构造一个仅包含username的对象
        UsernameRes usernameRes = new UsernameRes(currentUser.getUsername());
        usernameRes.setUsername(currentUser.getUsername());

        // 3.返回
        return CommonResult.success(usernameRes);
    }

    /**
     * 注销
     */
    @GetMapping("/logout")
    public CommonResult logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();

        return CommonResult.success(null);
    }

    /**
     * 获取当前整体健康分
     */
    @GetMapping("/getCurrentTotalHealthScore")
    public CommonResult getCurrentTotalHealthScore(){

        // 1.获得数据
        Double currentTotalHealthScore = totalHealthScoreService.getCurrentTotalHealthScore();

        // 2.返回
        return CommonResult.success(new CurrentTotalHealthScoreRes(currentTotalHealthScore));
    }

    /**
     * 获取过去七日整体健康分变化趋势
     */
    @GetMapping("/get7DaysTotalHealthScoreLine")
    public CommonResult get7DaysTotalHealthScoreLine(){

        // 1.获得数据
        ScoreLineVO line = totalHealthScoreService.get7DaysTotalHealthScoreLine();

        // 2.返回
        return CommonResult.success(line);
    }

    /**
     * 拉取表单数据
     */
    @GetMapping("/loadTableManageForm")
    public CommonResult loadTableManageForm(){

        // 1.拉取表单数据
        List<TableManageFormRes> tableManageFormResList = tableManageFormService.loadTableManageForm();

        // 2.构造返回结果
        CommonFormDataRes<TableManageFormRes> tableManageVOCommonResult = new CommonFormDataRes<>(tableManageFormResList, tableManageFormResList.size());

        // 3.返回
        return CommonResult.success(tableManageVOCommonResult);
    }
}
