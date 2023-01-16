package com.pedro.domain.form.model.res;

/**
 * 库表约束页表单展示
 */
public class TableRuleFormRes {

    private int rid;

    private int cid;

    private String columnName;

    private String ruleType;

    /**
     * 指定值/指定数值范围
     */
    private String requiredValue;

    /**
     * 要求比例/要求出现次数
     */
    private String requiredRange;

    private String withAlarm;

    private int weight;

    public TableRuleFormRes() {
    }

    public TableRuleFormRes(int rid, int cid, String columnName, String ruleType, String requiredValue, String requiredRange, String withAlarm, int weight) {
        this.rid = rid;
        this.cid = cid;
        this.columnName = columnName;
        this.ruleType = ruleType;
        this.requiredValue = requiredValue;
        this.requiredRange = requiredRange;
        this.withAlarm = withAlarm;
        this.weight = weight;
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

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getRuleType() {
        return ruleType;
    }

    public void setRuleType(String ruleType) {
        this.ruleType = ruleType;
    }

    public String getRequiredValue() {
        return requiredValue;
    }

    public void setRequiredValue(String requiredValue) {
        this.requiredValue = requiredValue;
    }

    public String getRequiredRange() {
        return requiredRange;
    }

    public void setRequiredRange(String requiredRange) {
        this.requiredRange = requiredRange;
    }

    public String getWithAlarm() {
        return withAlarm;
    }

    public void setWithAlarm(String withAlarm) {
        this.withAlarm = withAlarm;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
