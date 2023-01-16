package com.pedro.interfaces.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class TableRuleController {

    /**
     * 跳转到约束管理页
     */
    @GetMapping("/jumpToRulePage")
    public ModelAndView jumpToRulePage(int tid) {
        ModelAndView mv = new ModelAndView("rule.html");
        mv.addObject("tid", tid);
        return mv;
    }
}
