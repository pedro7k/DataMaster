package com.pedro.domain.dbProcess.model.req;

import java.util.Map;

/**
 * 可视化创建表 入参
 */
public class TableCreationReq {

    private String tableName;

    private int tableWeight;

    private Map<String, ColumnReq> columns;

    public TableCreationReq() {
    }

    public TableCreationReq(String tableName, int tableWeight, Map<String, ColumnReq> columns) {
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

    public Map<String, ColumnReq> getColumns() {
        return columns;
    }

    public void setColumns(Map<String, ColumnReq> columns) {
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
