package com.pedro.domain.dbProcess.model.vo;

public class TableInfoVO {

    String tableName;

    int tableWeight;

    public TableInfoVO() {
    }

    public TableInfoVO(String tableName, int tableWeight) {
        this.tableName = tableName;
        this.tableWeight = tableWeight;
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
}
