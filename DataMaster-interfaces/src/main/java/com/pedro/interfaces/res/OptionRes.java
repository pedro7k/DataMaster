package com.pedro.interfaces.res;

import com.pedro.domain.form.model.vo.OptionVO;

import java.util.ArrayList;
import java.util.List;

public class OptionRes {

    private List<OptionVO> options;

    public OptionRes() {
        options = new ArrayList<>();
    }

    public OptionRes(List<OptionVO> options) {
        this.options = options;
    }

    public List<OptionVO> getOptions() {
        return options;
    }

    public void setOptions(List<OptionVO> options) {
        this.options = options;
    }
}
