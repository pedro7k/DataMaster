package com.pedro.interfaces.controller;

import com.pedro.common.res.CommonResult;
import com.pedro.domain.user.model.vo.UserVO;
import com.pedro.domain.user.model.vo.UsernameVO;
import com.pedro.interfaces.role.ShiroUtil;
import org.apache.catalina.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

/**
 * 库表管理页Controller
 */
@RestController
public class TableManageController {

    private static final Logger logger = LoggerFactory.getLogger(TableManageController.class);

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
        UsernameVO usernameVO = new UsernameVO(currentUser.getUsername());
        usernameVO.setUsername(currentUser.getUsername());

        // 3.返回
        return CommonResult.success(usernameVO);
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
}
