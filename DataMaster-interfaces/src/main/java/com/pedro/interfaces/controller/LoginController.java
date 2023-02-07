package com.pedro.interfaces.controller;

import com.google.common.collect.Lists;
import com.pedro.auth.common.enums.EncryptionEnum;
import com.pedro.auth.common.exceptions.PedroAuthException;
import com.pedro.auth.model.User;
import com.pedro.auth.subject.AuthSubject;
import com.pedro.auth.subject.UserAccessFunction;
import com.pedro.auth.subject.api.PedroAuthUtil;
import com.pedro.common.exceptions.ServiceException;
import com.pedro.common.res.CommonResult;
import com.pedro.common.enums.ServiceExceptionEnum;
import com.pedro.domain.user.model.vo.UserVO;
import com.pedro.infrastructure.dao.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 登录Controller
 */
@RestController
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Resource
    private UserDao userDao;

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

        // 1.pedroAuth 获取当前subject,封装登陆数据
        AuthSubject subject = PedroAuthUtil.getAuthSubject();

        // 2.登录
        try {
            subject.login(username, passWord, EncryptionEnum.MD5_ENCRYPTION, this::getUserInfo);
            return CommonResult.success(null, "登陆成功！");
        } catch (PedroAuthException e) {
            // 登陆过程中出现异常
            logger.warn("登陆失败", e);
            return CommonResult.error(null, e.getMessage());
        } catch (Throwable e) {
            logger.error("登陆时出现异常", e);
            e.printStackTrace();
            throw new ServiceException(ServiceExceptionEnum.SYS_ERROR);
        }
    }

    /**
     * 获取User信息，用于登陆验证
     *
     * @param username
     * @return
     */
    private User getUserInfo(String username) {

        // 1.向DB查询
        UserVO userFromDB = userDao.queryUserByName(username);
        User user = new User();

        // 2.构造结果
        user.setUsername(userFromDB.getUsername());
        user.setPassword(userFromDB.getPassword());
        user.setSalt(userFromDB.getSalt());
        List<String> roleList = new ArrayList<>();
        roleList.add(String.valueOf(userFromDB.getRole()));
        user.setRoleList(roleList);

        // 3.返回
        return user;
    }
}
