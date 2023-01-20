package com.pedro.domain.dbProcess.model.vo;

/**
 * 表单扫描频率VO，用于定时任务决策是否扫描
 */
public class TableScanFreqVO {

    int tid;

    int scanFreqPerMin;

    public TableScanFreqVO() {
    }

    public TableScanFreqVO(int tid, int scanFreqPerMin) {
        this.tid = tid;
        this.scanFreqPerMin = scanFreqPerMin;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public int getScanFreqPerMin() {
        return scanFreqPerMin;
    }

    public void setScanFreqPerMin(int scanFreqPerMin) {
        this.scanFreqPerMin = scanFreqPerMin;
    }
}
