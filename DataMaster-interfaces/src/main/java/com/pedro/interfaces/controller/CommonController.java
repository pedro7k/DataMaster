package com.pedro.interfaces.controller;

import com.pedro.domain.user.model.vo.UserVO;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
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
        logger.info("[roleDenied]权限不足，user={},time={}", "test", new Date());
        ModelAndView mv = new ModelAndView("roleDenied.html");
        return mv;
    }
}
