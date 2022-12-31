package com.pedro.interfaces.controller;

import com.pedro.common.res.CommonResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test")
    public void test(){
        System.out.println("test");
    }

    @PostMapping("/testWithParam")
    public CommonResult testWithParam(@RequestParam("id") String id,@RequestParam("password") String passWord){
        System.out.println(id);
        System.out.println(passWord);
        return CommonResult.success(null);
    }
}
