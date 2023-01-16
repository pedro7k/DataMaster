package com.pedro.domain.form.model.vo;

public class PieDataVO {

    String name;

    int value = 0;

    public PieDataVO() {
    }

    public PieDataVO(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    /**
     * value自增
     */
    public void incValue(){
        value++;
    }
}
