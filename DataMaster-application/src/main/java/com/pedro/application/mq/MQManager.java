package com.pedro.application.mq;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.pedro.application.mq.consumer.TableScanEventConsumer;
import com.pedro.application.mq.factory.TableScanMessageFactory;
import com.pedro.application.mq.model.TableScanMessage;
import com.pedro.event.PedroEventPlane;
import com.pedro.event.common.enums.ProviderTypeEnum;
import com.pedro.event.common.enums.ProviderWaitStrategyEnum;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQManager {

    @Bean("TableScanMessageEventPlane")
    public PedroEventPlane<TableScanMessage> TableScanMessageEventPlane() {

        // 1.构建队列
        PedroEventPlane<TableScanMessage> plane = new PedroEventPlane
                .Builder<>(Executors.newFixedThreadPool(2), new TableScanMessageFactory(), 1024 * 256, ProviderTypeEnum.SINGLE_PROVIDER, new TableScanEventConsumer())
                .providerWeightStrategy(ProviderWaitStrategyEnum.PROVIDER_PARK_1NS_STRATEGY)
                .build();

        // 2.启动disruptor线程
        plane.start();

        // 3.返回队列
        return plane;
    }
}

/*@Configuration
public class MQManager {

    @Bean("TableScanMessageRingBuffer")
    public RingBuffer<TableScanMessage> TableScanMessageRingBuffer() {

        // 1.定义用于事件处理的线程池， Disruptor通过java.util.concurrent.ExecutorSerivce提供的线程来触发consumer的事件处理
        ExecutorService executor = Executors.newFixedThreadPool(2);

        // 2.指定事件工厂
        TableScanMessageFactory factory = new TableScanMessageFactory();

        // 3.指定ringBuffer字节大小，必须为2的N次方（能将求模运算转为位运算提高效率），否则将影响效率
        int bufferSize = 1024 * 256;

        // 4.单线程模式，获取额外的性能
        Disruptor<TableScanMessage> disruptor = new Disruptor<>(factory, bufferSize, executor,
                ProducerType.SINGLE, new BlockingWaitStrategy());

        // 5.设置事件业务处理器---消费者
        disruptor.handleEventsWith(new TableScanEventConsumer());

        // 6.启动disruptor线程
        disruptor.start();

        // 7.获取ringBuffer环，用于接取生产者生产的事件
        RingBuffer<TableScanMessage> ringBuffer = disruptor.getRingBuffer();

        return ringBuffer;
    }
}*/
