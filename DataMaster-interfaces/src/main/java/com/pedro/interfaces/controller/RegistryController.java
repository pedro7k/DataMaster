package com.pedro.interfaces.controller;

import com.pedro.common.res.CommonResult;
import com.pedro.domain.user.model.req.RegistryReq;
import com.pedro.domain.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 注册Controller
 */
@RestController
public class RegistryController {

    @Resource
    private UserService userServiceImpl;

    /**
     * 注册接口
     */
    @PostMapping("/registry")
    public CommonResult registry(@RequestParam("username") String username, @RequestParam("password") String passWord, @RequestParam("rePassword") String rePassword) {
        System.out.println("用户注册");
        System.out.println("username=" + username);
        System.out.println("passWord=" + passWord);

        // 1.注册
        RegistryReq registryReq = new RegistryReq(username, passWord, rePassword);
        userServiceImpl.registry(registryReq);

        // 2.成功返回、跳转
        return CommonResult.success(null);
    }


}
