package com.pedro.domain.dbProcess.model.res;

public class TableScanRes {

    int weight;

    boolean rulePass;

    public TableScanRes() {
    }

    public TableScanRes(int weight, boolean rulePass) {
        this.weight = weight;
        this.rulePass = rulePass;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public boolean isRulePass() {
        return rulePass;
    }

    public void setRulePass(boolean rulePass) {
        this.rulePass = rulePass;
    }

    @Override
    public String toString() {
        return "TableScanRes{" +
                "weight=" + weight +
                ", rulePass=" + rulePass +
                '}';
    }
}
