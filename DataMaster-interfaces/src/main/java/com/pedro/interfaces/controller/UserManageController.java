package com.pedro.interfaces.controller;

import com.google.common.base.Splitter;
import com.pedro.auth.annotation.MethodAuth;
import com.pedro.auth.common.enums.RuleLevelEnum;
import com.pedro.common.enums.UserRoleEnum;
import com.pedro.common.res.CommonResult;
import com.pedro.domain.form.model.res.UserManageFormRes;
import com.pedro.domain.form.service.userManageForm.UserManageFormService;
import com.pedro.interfaces.res.CommonFormDataRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户管理页Controller
 */
@RestController
public class UserManageController {

    private static final Logger logger = LoggerFactory.getLogger(UserManageController.class);

    /**
     * ROOT权限对应的代码
     */
    private static final String ROOT_CODE = "0";

    @Resource
    private UserManageFormService userManageFormService;

    /**
     * 跳转到用户管理页
     * 需要ROOT权限
     */
    @GetMapping("/jumpToUserManage")
    @MethodAuth(level = RuleLevelEnum.NEED_ROLE, roles = ROOT_CODE)
    public ModelAndView jumpToUserManage() {
        ModelAndView mv = new ModelAndView("userManage.html");
        return mv;
    }

    /**
     * 用户管理表单数据拉取接口
     */
    @GetMapping("/loadUserManageForm")
    public CommonResult loadUserManageForm() {
        // 1.拉取表单数据
        List<UserManageFormRes> userManageFormResList = userManageFormService.loadUserManageForm();

        // 2.构造返回结果
        CommonFormDataRes<UserManageFormRes> commonFormDataRes = new CommonFormDataRes<>(userManageFormResList, userManageFormResList.size());

        // 3.返回
        return CommonResult.success(commonFormDataRes);
    }

    /**
     * 删除单个用户
     */
    @PostMapping("/deleteUser")
    public CommonResult deleteUser(@RequestParam("uid") int uid) {

        userManageFormService.deleteUser(uid);

        return CommonResult.success(null, "删除成功");
    }

    /**
     * 批量删除用户
     */
    @PostMapping("/batchDeleteUser")
    public CommonResult batchDeleteUser(@RequestParam("ids") String uids) {

        // 1.获取uid列表
        List<Integer> uidList = Splitter.on(",").trimResults().omitEmptyStrings().splitToList(uids)
                .stream().map(Integer::parseInt).collect(Collectors.toList());

        // 2.批量删除
        userManageFormService.batchDeleteUsers(uidList);

        return CommonResult.success(null, "删除成功");
    }


    /**
     * 快速编辑用户权限
     */
    @PostMapping("/quickEditRole")
    public CommonResult quickEditRole(@RequestParam("uid") int uid, @RequestParam("role") String role) {

        userManageFormService.editUserRole(uid, role);

        return CommonResult.success(null, "修改成功！");
    }


}
