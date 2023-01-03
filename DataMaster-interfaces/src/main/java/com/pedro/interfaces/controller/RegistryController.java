package com.pedro.interfaces.controller;

import com.pedro.common.exceptions.ServiceException;
import com.pedro.common.res.CommonResult;
import com.pedro.common.res.ServiceExceptionEnum;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 注册Controller
 */
@RestController
public class RegistryController {

    /**
     * 注册接口
     */
    @PostMapping("/registry")
    public CommonResult registry(@RequestParam("username") String username, @RequestParam("password") String passWord, @RequestParam("rePassword") String rePassword){
        System.out.println("用户注册");
        System.out.println("username="+username);
        System.out.println("passWord="+passWord);

        // 1.密码校验
        if(!passWord.equals(rePassword)){
            throw new ServiceException(ServiceExceptionEnum.REPEAT_PASSWORD_ERROR);
        }

        // 2.权限控制

        // 3.用户落库

        // 4.成功返回、跳转
        return CommonResult.success(null);
    }


}
