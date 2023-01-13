package com.pedro.domain.dbProcess.model.vo;

public class TableCreationColumnVO {

    String columnName;

    String dataType;

    boolean primary;

    boolean notNull;

    boolean hasDefaultValue;

    String defaultValue;

    public TableCreationColumnVO() {
    }

    public TableCreationColumnVO(String columnName, String dataType, boolean primary, boolean notNull, boolean hasDefaultValue, String defaultValue) {
        this.columnName = columnName;
        this.dataType = dataType;
        this.primary = primary;
        this.notNull = notNull;
        this.hasDefaultValue = hasDefaultValue;
        this.defaultValue = defaultValue;
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

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    @Override
    public String toString() {
        return "TableCreationColumnVO{" +
                "columnName='" + columnName + '\'' +
                ", dataType='" + dataType + '\'' +
                ", primary=" + primary +
                ", notNull=" + notNull +
                ", hasDefaultValue=" + hasDefaultValue +
                ", defaultValue='" + defaultValue + '\'' +
                '}';
    }
}
