package com.pedro.domain.dbProcess.model.req;

public class RuleReq {

    private String ruleType;

    private int ruleWeight;

    private String appearRatio;

    private String appearTimes;

    private String valueAppear;

    private String valueRange;

    public RuleReq() {
    }

    public RuleReq(String ruleType, int ruleWeight, String appearRatio, String appearTimes, String valueAppear, String valueRange) {
        this.ruleType = ruleType;
        this.ruleWeight = ruleWeight;
        this.appearRatio = appearRatio;
        this.appearTimes = appearTimes;
        this.valueAppear = valueAppear;
        this.valueRange = valueRange;
    }

    public String getRuleType() {
        return ruleType;
    }

    public void setRuleType(String ruleType) {
        this.ruleType = ruleType;
    }

    public int getRuleWeight() {
        return ruleWeight;
    }

    public void setRuleWeight(int ruleWeight) {
        this.ruleWeight = ruleWeight;
    }

    public String getAppearRatio() {
        return appearRatio;
    }

    public void setAppearRatio(String appearRatio) {
        this.appearRatio = appearRatio;
    }

    public String getAppearTimes() {
        return appearTimes;
    }

    public void setAppearTimes(String appearTimes) {
        this.appearTimes = appearTimes;
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

    @Override
    public String toString() {
        return "RuleReq{" +
                "ruleType='" + ruleType + '\'' +
                ", ruleWeight=" + ruleWeight +
                ", appearRatio='" + appearRatio + '\'' +
                ", appearTimes='" + appearTimes + '\'' +
                ", valueAppear='" + valueAppear + '\'' +
                ", valueRange='" + valueRange + '\'' +
                '}';
    }
}

