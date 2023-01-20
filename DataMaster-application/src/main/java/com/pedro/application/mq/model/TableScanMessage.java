package com.pedro.application.mq.model;

import java.util.Date;
import java.util.List;

/**
 * 表单扫描消息
 */
public class TableScanMessage {

    /**
     * 消息发送时间
     */
    private Date sendTime;

    /**
     * 需要扫描的tid列表
     */
    private List<Integer> tidList;

    public TableScanMessage() {
    }

    public TableScanMessage(Date sendTime, List<Integer> tidList) {
        this.sendTime = sendTime;
        this.tidList = tidList;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public List<Integer> getTidList() {
        return tidList;
    }

    public void setTidList(List<Integer> tidList) {
        this.tidList = tidList;
    }

    @Override
    public String toString() {
        return "TableScanMessage{" +
                "sendTime=" + sendTime +
                ", tidList=" + tidList +
                '}';
    }
}
