package com.pedro.interfaces.res;

import com.pedro.domain.form.model.vo.PieDataVO;

import java.util.ArrayList;
import java.util.List;

public class PieDataRes {

    private List<PieDataVO> pieData;

    public PieDataRes() {
        pieData = new ArrayList<>();
    }

    public PieDataRes(List<PieDataVO> pieData) {
        this.pieData = pieData;
    }

    public List<PieDataVO> getPieData() {
        return pieData;
    }

    public void setPieData(List<PieDataVO> pieData) {
        this.pieData = pieData;
    }
}
