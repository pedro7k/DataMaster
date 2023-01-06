package com.pedro.interfaces.controller;

import com.pedro.common.res.CommonResult;
import com.pedro.domain.form.model.res.UserManageFormRes;
import com.pedro.domain.form.model.vo.UserManageVO;
import com.pedro.domain.form.service.userManageForm.UserManageFormService;
import com.pedro.interfaces.res.FormDataRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户管理页Controller
 */
@RestController
public class UserManageController {

    private static final Logger logger = LoggerFactory.getLogger(UserManageController.class);

    @Resource
    private UserManageFormService userManageFormService;

    /**
     * 跳转到用户管理页
     */
    @GetMapping("/jumpToUserManage")
    public ModelAndView jumpToUserManage() {
        ModelAndView mv = new ModelAndView("userManage.html");
        return mv;
    }

    /**
     * 用户管理表单数据拉取接口
     */
    @GetMapping("/loadUserManageForm")
    public CommonResult loadUserManageForm(){
        // 1.拉取表单数据
        List<UserManageFormRes> userManageFormResList = userManageFormService.loadUserManageForm();

        // 2.构造返回结果
        FormDataRes<UserManageFormRes> formDataRes = new FormDataRes<>(userManageFormResList, userManageFormResList.size());

        // 3.返回
        return CommonResult.success(formDataRes);
    }

    /**
     * 删除单个用户
     */
    @PostMapping("/deleteUser")
    public CommonResult deleteUser(@RequestParam("uid") int uid){

        userManageFormService.deleteUser(uid);

        return CommonResult.success(null);
    }



}
