package com.pedro.domain.dbProcess.model.req;

import java.util.Map;

/**
 * 可视化创建表 入参
 */
public class TableCreationReq {

    private String tableName;

    private int tableWeight;

    private Map<String, Column> columns;

    public TableCreationReq() {
    }

    public TableCreationReq(String tableName, int tableWeight, Map<String, Column> columns) {
        this.tableName = tableName;
        this.tableWeight = tableWeight;
        this.columns = columns;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public int getTableWeight() {
        return tableWeight;
    }

    public void setTableWeight(int tableWeight) {
        this.tableWeight = tableWeight;
    }

    public Map<String, Column> getColumns() {
        return columns;
    }

    public void setColumns(Map<String, Column> columns) {
        this.columns = columns;
    }

    @Override
    public String toString() {
        return "TableCreationReq{" +
                "tableName='" + tableName + '\'' +
                ", tableWeight=" + tableWeight +
                ", columns=" + columns +
                '}';
    }
}

class Column{

    private String dataType;

    private boolean primary;

    private boolean notNull;

    private boolean hasDefaultValue;

    private String defaultValue;

    Map<String, Rule> rules;

    public Column() {
    }

    public Column(String dataType, boolean primary, boolean notNull, boolean hasDefaultValue, String defaultValue, Map<String, Rule> rules) {
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

    public Map<String, Rule> getRules() {
        return rules;
    }

    public void setRules(Map<String, Rule> rules) {
        this.rules = rules;
    }

    @Override
    public String toString() {
        return "Column{" +
                "dataType='" + dataType + '\'' +
                ", primary=" + primary +
                ", notNull=" + notNull +
                ", hasDefaultValue=" + hasDefaultValue +
                ", defaultValue='" + defaultValue + '\'' +
                ", rules=" + rules +
                '}';
    }
}

class Rule{

    private String ruleType;

    private int ruleWeight;

    private String appearRatio;

    private String appearTimes;

    private String valueAppear;

    private String valueRange;

    public String getRuleType() {
        return ruleType;
    }

    public int getRuleWeight() {
        return ruleWeight;
    }

    public String getAppearRatio() {
        return appearRatio;
    }

    public String getAppearTimes() {
        return appearTimes;
    }

    public String getValueAppear() {
        return valueAppear;
    }

    public String getValueRange() {
        return valueRange;
    }

    public Rule() {
    }

    public Rule(String ruleType, int ruleWeight, String appearRatio, String appearTimes, String valueAppear, String valueRange) {
        this.ruleType = ruleType;
        this.ruleWeight = ruleWeight;
        this.appearRatio = appearRatio;
        this.appearTimes = appearTimes;
        this.valueAppear = valueAppear;
        this.valueRange = valueRange;
    }

    public void setRuleType(String ruleType) {
        this.ruleType = ruleType;
    }

    public void setRuleWeight(int ruleWeight) {
        this.ruleWeight = ruleWeight;
    }

    public void setAppearRatio(String appearRatio) {
        this.appearRatio = appearRatio;
    }

    public void setAppearTimes(String appearTimes) {
        this.appearTimes = appearTimes;
    }

    public void setValueAppear(String valueAppear) {
        this.valueAppear = valueAppear;
    }

    public void setValueRange(String valueRange) {
        this.valueRange = valueRange;
    }

    @Override
    public String toString() {
        return "Rule{" +
                "ruleType='" + ruleType + '\'' +
                ", ruleWeight=" + ruleWeight +
                ", appearRatio='" + appearRatio + '\'' +
                ", appearTimes='" + appearTimes + '\'' +
                ", valueAppear='" + valueAppear + '\'' +
                ", valueRange='" + valueRange + '\'' +
                '}';
    }
}
