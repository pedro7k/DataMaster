package com.pedro.domain.form.model.res;

/**
 * 表单详情页表单展示
 */
public class TableDetailFormRes {

    int cid;

    String columnName;

    String dataType;

    /**
     * 下列属性为中文是/否
     */
    String primary;

    String notNull;

    String withDefaultValue;

    String unique;

    String autoInc;

    int ruleCount;

    public TableDetailFormRes() {
    }

    public TableDetailFormRes(int cid, String columnName, String dataType, String primary, String notNull, String withDefaultValue, String unique, String autoInc, int ruleCount) {
        this.cid = cid;
        this.columnName = columnName;
        this.dataType = dataType;
        this.primary = primary;
        this.notNull = notNull;
        this.withDefaultValue = withDefaultValue;
        this.unique = unique;
        this.autoInc = autoInc;
        this.ruleCount = ruleCount;
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

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getPrimary() {
        return primary;
    }

    public void setPrimary(String primary) {
        this.primary = primary;
    }

    public String getNotNull() {
        return notNull;
    }

    public void setNotNull(String notNull) {
        this.notNull = notNull;
    }

    public String getWithDefaultValue() {
        return withDefaultValue;
    }

    public void setWithDefaultValue(String withDefaultValue) {
        this.withDefaultValue = withDefaultValue;
    }

    public String getUnique() {
        return unique;
    }

    public void setUnique(String unique) {
        this.unique = unique;
    }

    public String getAutoInc() {
        return autoInc;
    }

    public void setAutoInc(String autoInc) {
        this.autoInc = autoInc;
    }

    public int getRuleCount() {
        return ruleCount;
    }

    public void setRuleCount(int ruleCount) {
        this.ruleCount = ruleCount;
    }
}
