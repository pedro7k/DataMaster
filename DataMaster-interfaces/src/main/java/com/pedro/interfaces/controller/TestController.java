package com.pedro.interfaces.controller;

import com.pedro.common.res.CommonResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller测试类
 */
@RestController
public class TestController {

    @GetMapping("/test")
    public void test(){
        System.out.println("test");
    }

    @GetMapping("/testRole")
    public void testRole(){
        System.out.println("testRole");
    }

    @PostMapping("/testWithParam")
    public CommonResult testWithParam(@RequestParam("id") String id,@RequestParam("password") String passWord){
        System.out.println(id);
        System.out.println(passWord);
        return CommonResult.success(null);
    }

    @GetMapping("/jumpToAnotherPage1")
    public String jumpToAnotherPage1(){
        System.out.println("jump");
        return "test";
    }

    /**
     * 跳转页面
     * @return
     */
    @GetMapping("/jumpToAnotherPage2")
    public ModelAndView jumpToAnotherPage2(){
        System.out.println("jump");
        ModelAndView mv = new ModelAndView("test.html");
        return mv;
    }

    /**
     * 跳转页面2
     * @return
     */
    @GetMapping("/jumpToAnotherPage3")
    public ModelAndView jumpToAnotherPage3(){
        System.out.println("jump");
        ModelAndView mv = new ModelAndView("login.html");
        return mv;
    }
}
