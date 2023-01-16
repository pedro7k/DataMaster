package com.pedro.infrastructure.po;

public class TableRulePO {

    int rid;

    int cid;

    int tid;

    int ruleWeight;

    int type;

    String valueAppear;

    String valueRange;

    String appearTimes;

    String appearRatio;

    String extInfo;

    public TableRulePO() {
    }

    public TableRulePO(int rid, int cid, int tid, int ruleWeight, int type, String valueAppear, String valueRange, String appearTimes, String appearRatio, String extInfo) {
        this.rid = rid;
        this.cid = cid;
        this.tid = tid;
        this.ruleWeight = ruleWeight;
        this.type = type;
        this.valueAppear = valueAppear;
        this.valueRange = valueRange;
        this.appearTimes = appearTimes;
        this.appearRatio = appearRatio;
        this.extInfo = extInfo;
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

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public int getRuleWeight() {
        return ruleWeight;
    }

    public void setRuleWeight(int ruleWeight) {
        this.ruleWeight = ruleWeight;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getValueAppear() {
        return valueAppear;
    }

    public void setValueAppear(String valueAppear) {
        this.valueAppear = valueAppear;
    }

    public String getValueRange() {
        return valueRange;
    }

    public void setValueRange(String valueRange) {
        this.valueRange = valueRange;
    }

    public String getAppearTimes() {
        return appearTimes;
    }

    public void setAppearTimes(String appearTimes) {
        this.appearTimes = appearTimes;
    }

    public String getAppearRatio() {
        return appearRatio;
    }

    public void setAppearRatio(String appearRatio) {
        this.appearRatio = appearRatio;
    }

    public String getExtInfo() {
        return extInfo;
    }

    public void setExtInfo(String extInfo) {
        this.extInfo = extInfo;
    }
}
