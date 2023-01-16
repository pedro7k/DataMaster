package com.pedro.interfaces.controller;

import com.pedro.common.res.CommonResult;
import com.pedro.domain.form.model.res.TableDetailFormRes;
import com.pedro.domain.form.model.res.TableRuleFormRes;
import com.pedro.domain.form.model.vo.PieDataVO;
import com.pedro.domain.form.service.tableRuleForm.TableRuleFormService;
import com.pedro.interfaces.res.CommonFormDataRes;
import com.pedro.interfaces.res.PieDataRes;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class TableRuleController {

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
    public CommonResult getRulePieData(int tid){

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
}
