package com.pedro.domain.form.model.vo;

/**
 * 选项VO
 */
public class OptionVO {

    private String label;

    private Integer value;

    public OptionVO() {
    }

    public OptionVO(String label, Integer value) {
        this.label = label;
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
