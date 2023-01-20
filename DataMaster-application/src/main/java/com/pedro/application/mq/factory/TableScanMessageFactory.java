package com.pedro.application.mq.factory;

import com.lmax.disruptor.EventFactory;
import com.pedro.application.mq.model.TableScanMessage;

/**
 * 消息生产工厂
 */
public class TableScanMessageFactory implements EventFactory<TableScanMessage> {

    @Override
    public TableScanMessage newInstance() {
        return new TableScanMessage();
    }
}
