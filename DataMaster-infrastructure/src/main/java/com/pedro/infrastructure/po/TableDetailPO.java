package com.pedro.infrastructure.po;

/**
 * 列详情PO
 */
public class TableDetailPO {

    private int cid;

    private int tid;

    private String columnName;

    private String dataType;

    private boolean ifPrimary;

    private boolean autoInc;

    private boolean ifUnique;

    private boolean withDefaultValue;

    private boolean notNull;

    private boolean numType;

    public TableDetailPO() {
    }

    public TableDetailPO(int cid, int tid, String columnName, String dataType, boolean ifPrimary, boolean autoInc, boolean ifUnique, boolean withDefaultValue, boolean notNull, boolean numType) {
        this.cid = cid;
        this.tid = tid;
        this.columnName = columnName;
        this.dataType = dataType;
        this.ifPrimary = ifPrimary;
        this.autoInc = autoInc;
        this.ifUnique = ifUnique;
        this.withDefaultValue = withDefaultValue;
        this.notNull = notNull;
        this.numType = numType;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
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

    public boolean isNumType() {
        return numType;
    }

    public void setNumType(boolean numType) {
        this.numType = numType;
    }
}
