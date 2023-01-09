package com.pedro.domain.form.model.vo;

public class TableWeightVO {

    int tid;

    int weight;

    public TableWeightVO() {
    }

    public TableWeightVO(int tid, int weight) {
        this.tid = tid;
        this.weight = weight;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
