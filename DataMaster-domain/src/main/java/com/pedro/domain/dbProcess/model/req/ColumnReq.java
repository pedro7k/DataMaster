package com.pedro.domain.dbProcess.model.req;

import java.util.Map;

public class ColumnReq {

    private String dataType;

    private boolean primary;

    private boolean notNull;

    private boolean hasDefaultValue;

    private String defaultValue;

    Map<String, RuleReq> rules;

    public ColumnReq() {
    }

    public ColumnReq(String dataType, boolean primary, boolean notNull, boolean hasDefaultValue, String defaultValue, Map<String, RuleReq> rules) {
        this.dataType = dataType;
        this.primary = primary;
        this.notNull = notNull;
        this.hasDefaultValue = hasDefaultValue;
        this.defaultValue = defaultValue;
        this.rules = rules;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public boolean isPrimary() {
        return primary;
    }

    public void setPrimary(boolean primary) {
        this.primary = primary;
    }

    public boolean isNotNull() {
        return notNull;
    }

    public void setNotNull(boolean notNull) {
        this.notNull = notNull;
    }

    public boolean isHasDefaultValue() {
        return hasDefaultValue;
    }

    public void setHasDefaultValue(boolean hasDefaultValue) {
        this.hasDefaultValue = hasDefaultValue;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public Map<String, RuleReq> getRules() {
        return rules;
    }

    public void setRules(Map<String, RuleReq> rules) {
        this.rules = rules;
    }

    @Override
    public String toString() {
        return "ColumnReq{" +
                "dataType='" + dataType + '\'' +
                ", primary=" + primary +
                ", notNull=" + notNull +
                ", hasDefaultValue=" + hasDefaultValue +
                ", defaultValue='" + defaultValue + '\'' +
                ", rules=" + rules +
                '}';
    }
}
