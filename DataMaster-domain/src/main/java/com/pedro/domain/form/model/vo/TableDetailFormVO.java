package com.pedro.domain.form.model.vo;

public class TableDetailFormVO {

    private int cid;

    private String columnName;

    private String dataType;

    private boolean ifPrimary;

    private boolean autoInc;

    private boolean ifUnique;

    private boolean withDefaultValue;

    private boolean notNull;

    public TableDetailFormVO() {
    }

    public TableDetailFormVO(int cid, String columnName, String dataType, boolean ifPrimary, boolean autoInc, boolean ifUnique, boolean withDefaultValue, boolean notNull) {
        this.cid = cid;
        this.columnName = columnName;
        this.dataType = dataType;
        this.ifPrimary = ifPrimary;
        this.autoInc = autoInc;
        this.ifUnique = ifUnique;
        this.withDefaultValue = withDefaultValue;
        this.notNull = notNull;
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

    public boolean isIfPrimary() {
        return ifPrimary;
    }

    public void setIfPrimary(boolean ifPrimary) {
        this.ifPrimary = ifPrimary;
    }

    public boolean isAutoInc() {
        return autoInc;
    }

    public void setAutoInc(boolean autoInc) {
        this.autoInc = autoInc;
    }

    public boolean isIfUnique() {
        return ifUnique;
    }

    public void setIfUnique(boolean ifUnique) {
        this.ifUnique = ifUnique;
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
}
