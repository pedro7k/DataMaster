package com.pedro.application.mq.producer;

import java.util.List;

public interface TableScanEventProducer {

    /**
     * 发送表单扫描消息
     */
    void sendTableScanMessage(List<Integer> tidList);
}
