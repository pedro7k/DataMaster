package com.pedro.application.mq.factory;

import com.lmax.disruptor.EventFactory;
import com.pedro.application.mq.model.TableScanMessage;
import com.pedro.event.interfaces.MessageFactory;

/**
 * 消息生产工厂
 */
public class TableScanMessageFactory implements MessageFactory<TableScanMessage> {

    @Override
    public TableScanMessage createEmptyMessage() {
        return new TableScanMessage();
    }
}
/*public class TableScanMessageFactory implements EventFactory<TableScanMessage> {

    @Override
    public TableScanMessage newInstance() {
        return new TableScanMessage();
    }
}*/
