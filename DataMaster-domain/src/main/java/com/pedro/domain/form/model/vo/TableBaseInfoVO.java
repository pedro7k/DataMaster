package com.pedro.domain.form.model.vo;

/**
 * 表基本信息VO
 */
public class TableBaseInfoVO {

    int tid;

    String name;

    int weight;

    public TableBaseInfoVO() {
    }

    public TableBaseInfoVO(int tid, String name, int weight) {
        this.tid = tid;
        this.name = name;
        this.weight = weight;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
