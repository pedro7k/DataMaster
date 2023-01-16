package com.pedro.domain.form.model.vo;

public class RuleWeightVO {

    private int rid;

    private int weight;

    public RuleWeightVO() {
    }

    public RuleWeightVO(int rid, int weight) {
        this.rid = rid;
        this.weight = weight;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
