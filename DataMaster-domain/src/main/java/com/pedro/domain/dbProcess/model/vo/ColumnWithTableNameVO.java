package com.pedro.domain.dbProcess.model.vo;

public class ColumnWithTableNameVO {

    String columnName;

    String dataType;

    boolean primary;

    boolean notNull;

    boolean hasDefaultValue;

    String defaultValue;

    String tableName;

    public ColumnWithTableNameVO() {
    }

    public ColumnWithTableNameVO(String columnName, String dataType, boolean primary, boolean notNull, boolean hasDefaultValue, String defaultValue, String tableName) {
        this.columnName = columnName;
        this.dataType = dataType;
        this.primary = primary;
        this.notNull = notNull;
        this.hasDefaultValue = hasDefaultValue;
        this.defaultValue = defaultValue;
        this.tableName = tableName;
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

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}
