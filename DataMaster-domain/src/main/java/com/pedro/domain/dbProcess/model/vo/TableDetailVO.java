package com.pedro.domain.dbProcess.model.vo;

/**
 * TableDetailVO
 */
public class TableDetailVO {

    private int tid;

    private String columnName;

    private String dataType;

    private boolean primary;

    private boolean autoInc;

    private boolean unique;

    private boolean withDefaultValue;

    private boolean notNull;

    private boolean numType;

    public TableDetailVO() {
    }

    public TableDetailVO(int tid, String columnName, String dataType, boolean primary, boolean autoInc, boolean unique, boolean withDefaultValue, boolean notNull, boolean numType) {
        this.tid = tid;
        this.columnName = columnName;
        this.dataType = dataType;
        this.primary = primary;
        this.autoInc = autoInc;
        this.unique = unique;
        this.withDefaultValue = withDefaultValue;
        this.notNull = notNull;
        this.numType = numType;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
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

    public boolean isAutoInc() {
        return autoInc;
    }

    public void setAutoInc(boolean autoInc) {
        this.autoInc = autoInc;
    }

    public boolean isUnique() {
        return unique;
    }

    public void setUnique(boolean unique) {
        this.unique = unique;
    }

    public boolean isWithDefaultValue() {
        return withDefaultValue;
    }

    public void setWithDefaultValue(boolean withDefaultValue) {
        this.withDefaultValue = withDefaultValue;
    }

    public boolean isNotNull() {
        return notNull;
    }

    public void setNotNull(boolean notNull) {
        this.notNull = notNull;
    }

    public boolean isNumType() {
        return numType;
    }

    public void setNumType(boolean numType) {
        this.numType = numType;
    }

    @Override
    public String toString() {
        return "TableDetailVO{" +
                "tid=" + tid +
                ", columnName='" + columnName + '\'' +
                ", dataType='" + dataType + '\'' +
                ", primary=" + primary +
                ", autoInc=" + autoInc +
                ", unique=" + unique +
                ", withDefaultValue=" + withDefaultValue +
                ", notNull=" + notNull +
                ", numType=" + numType +
                '}';
    }
}
