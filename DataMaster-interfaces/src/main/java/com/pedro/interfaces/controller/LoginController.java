package com.pedro.interfaces.controller;

import com.pedro.common.exceptions.ServiceException;
import com.pedro.common.res.CommonResult;
import com.pedro.common.enums.ServiceExceptionEnum;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 登录Controller
 */
@RestController
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    /**
     * 跳转到登录页面
     */
    @GetMapping("/jumpToLoginPage")
    public ModelAndView jumpToLoginPage() {
        ModelAndView mv = new ModelAndView("login.html");
        return mv;
    }

    /**
     * 登录接口
     */
    @PostMapping("/login")
    public CommonResult login(@RequestParam("username") String username, @RequestParam("password") String passWord) {

        // 1.shiro 获取当前subject,封装登陆数据
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, passWord);

        // 2.登录
        try {
            subject.login(token);
            return CommonResult.success(null, "登陆成功！");
        } catch (UnknownAccountException e) {
            // 用户名不存在
            logger.warn("用户名不存在", e);
            throw new ServiceException(ServiceExceptionEnum.UNKNOWN_ACCOUNT_ERROR);
        } catch (IncorrectCredentialsException e) {
            // 密码错误
            logger.warn("密码错误", e);
            throw new ServiceException(ServiceExceptionEnum.INCORRECT_PASSWORD);
        } catch (AuthenticationException e) {
            logger.error("登陆时出现异常", e);
            e.printStackTrace();
            throw new ServiceException(ServiceExceptionEnum.SYS_ERROR);
        }
    }
}
