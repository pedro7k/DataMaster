package com.pedro.interfaces.controller;

import com.pedro.common.enums.ServiceExceptionEnum;
import com.pedro.common.res.CommonResult;
import com.pedro.domain.user.model.vo.UserVO;
import com.pedro.interfaces.role.ShiroConfig;
import com.pedro.interfaces.role.ShiroUtil;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

/**
 * 通用Controller
 */
@RestController
public class CommonController {

    private static final Logger logger = LoggerFactory.getLogger(CommonController.class);

    /**
     * 跳转到权限不足页面
     */
    @GetMapping("/roleDenied")
    public ModelAndView roleDenied() {
        logger.info("[roleDenied]权限不足，user={},time={}", ShiroUtil.getCurrentUser().getUsername(), new Date());
        ModelAndView mv = new ModelAndView("roleDenied.html");
        return mv;
    }

    /**
     * 非跳转校验权限,权限不足返回错误
     */
    @GetMapping("/roleCheck")
    public CommonResult roleCheck(@RequestParam int minRoleKey) {

        // 1.获取当前权限
        UserVO currentUser = ShiroUtil.getCurrentUser();
        int role = currentUser.getRole();

        // 2.权限不足
        if (role > minRoleKey) {
            logger.info("[roleDenied]权限不足，user={},time={}", currentUser.getUsername(), new Date());
            return CommonResult.error(ServiceExceptionEnum.ROLE_DENIED);
        }

        // 3.成功返回
        return CommonResult.success(null,"鉴权通过");

    }
}
