package com.pedro.application.mq.producer.impl;

import com.lmax.disruptor.RingBuffer;
import com.pedro.application.mq.consumer.TableScanEventConsumer;
import com.pedro.application.mq.model.TableScanMessage;
import com.pedro.application.mq.producer.TableScanEventProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Component
public class TableScanEventProducerImpl implements TableScanEventProducer {

    private static final Logger logger = LoggerFactory.getLogger(TableScanEventConsumer.class);

    @Resource
    private RingBuffer<TableScanMessage> messageRingBuffer;

    @Override
    public void sendTableScanMessage(List<Integer> tidList) {

        // 1.获取下一个Event槽的下标
        long sequence = messageRingBuffer.next();

        // 2.尝试发送消息
        try {
            // 指定位置填充数据
            TableScanMessage message = messageRingBuffer.get(sequence);
            message.setSendTime(new Date());
            message.setTidList(tidList);
            logger.info("向消息队列中添加消息, message={}", message);
        } catch (Throwable e) {
            logger.error("向消息队列中添加消息失败,tidList={}", tidList);
        } finally {
            // finally中必须调用publish，否则可能堵塞其他请求
            messageRingBuffer.publish(sequence);
        }

    }
}
