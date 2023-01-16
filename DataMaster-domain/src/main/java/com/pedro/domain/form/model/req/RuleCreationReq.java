package com.pedro.domain.form.model.req;

public class RuleCreationReq {

    private String cid;

    private String ruleType;

    private String ruleName;

    private Integer ruleWeight;

    private String appearRatio;

    private String appearTimes;

    private String valueAppear;

    private String valueRange;

    public RuleCreationReq() {
    }

    public RuleCreationReq(String cid, String ruleType, String ruleName, Integer ruleWeight, String appearRatio, String appearTimes, String valueAppear, String valueRange) {
        this.cid = cid;
        this.ruleType = ruleType;
        this.ruleName = ruleName;
        this.ruleWeight = ruleWeight;
        this.appearRatio = appearRatio;
        this.appearTimes = appearTimes;
        this.valueAppear = valueAppear;
        this.valueRange = valueRange;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getRuleType() {
        return ruleType;
    }

    public void setRuleType(String ruleType) {
        this.ruleType = ruleType;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public Integer getRuleWeight() {
        return ruleWeight;
    }

    public void setRuleWeight(Integer ruleWeight) {
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
        return "RuleCreationReq{" +
                "cid='" + cid + '\'' +
                ", ruleType='" + ruleType + '\'' +
                ", ruleName='" + ruleName + '\'' +
                ", ruleWeight=" + ruleWeight +
                ", appearRatio='" + appearRatio + '\'' +
                ", appearTimes='" + appearTimes + '\'' +
                ", valueAppear='" + valueAppear + '\'' +
                ", valueRange='" + valueRange + '\'' +
                '}';
    }
}
