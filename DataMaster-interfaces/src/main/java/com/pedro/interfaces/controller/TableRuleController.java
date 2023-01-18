package com.pedro.interfaces.controller;

import com.google.common.base.Splitter;
import com.pedro.common.enums.ServiceExceptionEnum;
import com.pedro.common.enums.UserRoleEnum;
import com.pedro.common.res.CommonResult;
import com.pedro.domain.form.model.req.RuleCreationReq;
import com.pedro.domain.form.model.res.TableDetailFormRes;
import com.pedro.domain.form.model.res.TableRuleFormRes;
import com.pedro.domain.form.model.vo.OptionVO;
import com.pedro.domain.form.model.vo.PieDataVO;
import com.pedro.domain.form.service.tableRuleForm.TableRuleFormService;
import com.pedro.domain.user.model.vo.UserVO;
import com.pedro.interfaces.res.CommonFormDataRes;
import com.pedro.interfaces.res.OptionRes;
import com.pedro.interfaces.res.PieDataRes;
import com.pedro.interfaces.role.ShiroUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class TableRuleController {

    private static final Logger logger = LoggerFactory.getLogger(TableManageController.class);

    @Resource
    private TableRuleFormService tableRuleFormService;

    /**
     * 跳转到约束管理页
     */
    @GetMapping("/jumpToRulePage")
    public ModelAndView jumpToRulePage(int tid) {
        ModelAndView mv = new ModelAndView("rule.html");
        mv.addObject("tid", tid);
        return mv;
    }

    /**
     * 获取约束饼图数据
     */
    @GetMapping("getRulePieData")
    public CommonResult getRulePieData(int tid) {

        // TODO crud数据变化时，同一页面上的饼图不能做到自动刷新

        // 1.拉取饼图数据
        List<PieDataVO> pieDataVOList = tableRuleFormService.loadRuleTypePie(tid);

        // 2.转变格式
        PieDataRes pieDataRes = new PieDataRes(pieDataVOList);

        // 3.返回
        return CommonResult.success(pieDataRes);
    }

    /**
     * 拉取约束管理页表单数据
     */
    @GetMapping("/loadTableRuleForm")
    public CommonResult loadTableRuleForm(int tid) {

        // 1.拉取表单数据
        List<TableRuleFormRes> tableRuleFormResList = tableRuleFormService.loadTableRuleForm(tid);

        // 2.构造返回结果
        CommonFormDataRes<TableRuleFormRes> tableRuleFormCommonResult = new CommonFormDataRes<>(tableRuleFormResList, tableRuleFormResList.size());

        // 3.返回
        return CommonResult.success(tableRuleFormCommonResult);
    }

    /**
     * 快速编辑约束权重
     */
    @PostMapping("/quickEditRuleWeight")
    public CommonResult quickEditRuleWeight(@RequestParam("rid") int rid, @RequestParam("weight") int weight) {

        // 1.获取当前权限
        UserVO currentUser = ShiroUtil.getCurrentUser();
        int role = currentUser.getRole();

        // 2.权限不足
        if (role > UserRoleEnum.ADMIN.getLevel()) {
            logger.info("[roleDenied]权限不足，user={},time={}", currentUser.getUsername(), new Date());
            return CommonResult.error(ServiceExceptionEnum.ROLE_DENIED);
        }

        // 3.编辑
        tableRuleFormService.editRuleWeight(rid, weight);

        // 4.返回
        return CommonResult.success(null, "修改成功！");
    }

    /**
     * 删除单个约束
     */
    @PostMapping("/deleteRule")
    public CommonResult deleteRule(@RequestParam("rid") int rid) {

        // 1.获取当前权限
        UserVO currentUser = ShiroUtil.getCurrentUser();
        int role = currentUser.getRole();

        // 2.权限不足
        if (role > UserRoleEnum.ADMIN.getLevel()) {
            logger.info("[roleDenied]权限不足，user={},time={}", currentUser.getUsername(), new Date());
            return CommonResult.error(ServiceExceptionEnum.ROLE_DENIED);
        }

        // 3.执行删除
        tableRuleFormService.deleteRule(rid);

        // 4.返回
        return CommonResult.success(null, "删除成功");
    }

    /**
     * 批量删除约束
     */
    @PostMapping("/batchDeleteRule")
    public CommonResult batchDeleteRule(@RequestParam("ids") String rids){

        // 1.获取当前权限
        UserVO currentUser = ShiroUtil.getCurrentUser();
        int role = currentUser.getRole();

        // 2.权限不足
        if (role > UserRoleEnum.ADMIN.getLevel()) {
            logger.info("[roleDenied]权限不足，user={},time={}", currentUser.getUsername(), new Date());
            return CommonResult.error(ServiceExceptionEnum.ROLE_DENIED);
        }

        // 3.获取id列表
        List<Integer> ridList = Splitter.on(",").trimResults().omitEmptyStrings().splitToList(rids)
                .stream().map(Integer::parseInt).collect(Collectors.toList());

        // 4.执行删除
        tableRuleFormService.batchDeleteRule(ridList);

        // 5.返回
        return CommonResult.success(null, "删除成功");
    }

    /**
     * 拉取创建约束时的列选项
     */
    @PostMapping("/loadColumnsByTid")
    public CommonResult loadColumnsByTid(int tid){

        // 1.获取数据
        List<OptionVO> optionList = tableRuleFormService.getColumnOptionList(tid);

        // 2.包装
        OptionRes optionRes = new OptionRes(optionList);

        // 3.返回
        return CommonResult.success(optionRes);
    }

    /**
     * 创建约束
     */
    @PostMapping("/createRule")
    public CommonResult createRule(@RequestBody RuleCreationReq ruleCreationReq){

        // 1.创建约束
        tableRuleFormService.createRule(ruleCreationReq);

        // 2.返回
        return CommonResult.success(null, "创建成功！请稍后刷新");
    }
}
