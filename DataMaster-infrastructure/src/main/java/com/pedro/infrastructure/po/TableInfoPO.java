package com.pedro.infrastructure.po;

public class TableInfoPO {

    private int tid;

    private String name;

    private int tableWeight;

    private int scanFreqPerMin;

    public TableInfoPO() {
    }

    public TableInfoPO(int tid, String name, int tableWeight, int scanFreqPerMin) {
        this.tid = tid;
        this.name = name;
        this.tableWeight = tableWeight;
        this.scanFreqPerMin = scanFreqPerMin;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTableWeight() {
        return tableWeight;
    }

    public void setTableWeight(int tableWeight) {
        this.tableWeight = tableWeight;
    }

    public int getScanFreqPerMin() {
        return scanFreqPerMin;
    }

    public void setScanFreqPerMin(int scanFreqPerMin) {
        this.scanFreqPerMin = scanFreqPerMin;
    }
}
