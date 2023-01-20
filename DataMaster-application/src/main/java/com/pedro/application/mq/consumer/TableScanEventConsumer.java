package com.pedro.application.mq.consumer;

import com.lmax.disruptor.EventHandler;
import com.pedro.application.mq.BeanManager;
import com.pedro.application.mq.model.TableScanMessage;
import com.pedro.application.process.DataScanProcess;
import com.pedro.application.process.impl.DataScanProcessImpl;
import com.pedro.domain.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.List;

/**
 * 表单扫描消息消费者
 */
public class TableScanEventConsumer implements EventHandler<TableScanMessage> {

    private static final Logger logger = LoggerFactory.getLogger(TableScanEventConsumer.class);

    private final DataScanProcess dataScanProcess = (DataScanProcess) BeanManager.getBean("dataScanProcessImpl");

    /**
     * 处理表单扫描消息
     *
     * @param message
     * @param sequence
     * @param endOfBatch
     */
    @Override
    public void onEvent(TableScanMessage message, long sequence, boolean endOfBatch) {

        try {
            if (message != null) {
                logger.info("开始消费表单扫描消息,sendTime={}", message.getSendTime());
                List<Integer> tidList = message.getTidList();
                for (Integer tid : tidList) {
                    // 表单扫描
                    dataScanProcess.tableDataScanProcess(tid);
                }
            }
        } catch (Throwable e) {
            logger.error("消费者处理表单扫描消息失败，message={}, errorInfo={}", message, e);
        }
    }
}
