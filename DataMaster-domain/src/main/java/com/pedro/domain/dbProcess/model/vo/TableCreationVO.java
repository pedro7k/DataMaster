package com.pedro.domain.dbProcess.model.vo;

import java.util.List;

public class TableCreationVO {

    String tableName;

    List<TableCreationColumnVO> columns;

    public TableCreationVO() {
    }

    public TableCreationVO(String tableName, List<TableCreationColumnVO> columns) {
        this.tableName = tableName;
        this.columns = columns;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<TableCreationColumnVO> getColumns() {
        return columns;
    }

    public void setColumns(List<TableCreationColumnVO> columns) {
        this.columns = columns;
    }
}


