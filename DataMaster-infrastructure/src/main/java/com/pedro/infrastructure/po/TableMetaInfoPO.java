package com.pedro.infrastructure.po;

/**
 * 表元信息PO
 */
public class TableMetaInfoPO {

    String field;

    String type;

    String Null;

    String Key;

    String Default;

    String extra;

    public TableMetaInfoPO() {
    }

    public TableMetaInfoPO(String field, String type, String aNull, String key, String aDefault, String extra) {
        this.field = field;
        this.type = type;
        Null = aNull;
        Key = key;
        Default = aDefault;
        this.extra = extra;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNull() {
        return Null;
    }

    public void setNull(String aNull) {
        Null = aNull;
    }

    public String getKey() {
        return Key;
    }

    public void setKey(String key) {
        Key = key;
    }

    public String getDefault() {
        return Default;
    }

    public void setDefault(String aDefault) {
        Default = aDefault;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }
}
