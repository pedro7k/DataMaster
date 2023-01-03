package com.pedro.interfaces.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 登录Controller
 */
@RestController
public class LoginController {

    /**
     * 跳转到登录页面
     */
    @GetMapping("/jumpToLoginPage")
    public ModelAndView jumpToAnotherPage2(){
        System.out.println("跳转到登录页面");
        ModelAndView mv = new ModelAndView("login.html");
        return mv;
    }
}
