package com.pedro.domain.form.model.vo;

/**
 * 表单约束基础VO
 */
public class TableRuleBaseVO {

    private int rid;

    int cid;

    private int type;

    private int ruleWeight;

    public TableRuleBaseVO() {
    }

    public TableRuleBaseVO(int rid, int cid, int type, int ruleWeight) {
        this.rid = rid;
        this.cid = cid;
        this.type = type;
        this.ruleWeight = ruleWeight;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getRuleWeight() {
        return ruleWeight;
    }

    public void setRuleWeight(int ruleWeight) {
        this.ruleWeight = ruleWeight;
    }
}
