package com.pedro.application.binlogMonitor;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.LoadingCache;
import com.github.shyiko.mysql.binlog.BinaryLogClient;
import com.github.shyiko.mysql.binlog.event.*;
import com.pedro.common.config.Constants;
import com.pedro.common.enums.ServiceExceptionEnum;
import com.pedro.common.exceptions.ServiceException;
import com.pedro.domain.dbProcess.service.tableMonitor.TableMonitorService;
import com.pedro.domain.form.model.res.TableDetailFormRes;
import com.pedro.domain.support.cache.CaffeineUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * binlog监听器，目前仅用于监控单表大量delete变更
 */
@Component
public class LogMonitor implements ApplicationRunner {

    private static final Logger logger = LoggerFactory.getLogger(LogMonitor.class);

    /**
     * 系统表前缀
     */
    private static final String SYS_TABLE_PREFIX = "sys_";

    @Resource
    private TableMonitorService tableMonitorService;

    /**
     * 删除计数器
     * key：mysql表id
     * value：存储delete时间的队列
     */
    private volatile LoadingCache<Long, TimerCounter> deleteCountMap;

    /**
     * mysql表id到表名映射
     */
    private volatile Cache<Long, String> tableIdMap;

    /**
     * 启动监听
     *
     * @param args
     * @throws Exception
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {

        // 1.构造缓存
        deleteCountMap = CaffeineUtil.buildLoadingCache(id -> new TimerCounter(Constants.DELETE_COUNT_TIME_LENGTH, Constants.DELETE_COUNT_THRESHOLD));
        tableIdMap = CaffeineUtil.buildCache();

        // 2.构造client
        BinaryLogClient client = new BinaryLogClient("localhost", 3306, "root", "123456");
        client.setServerId(2);

        // 3.填充事件处理机制
        client.registerEventListener(event -> {
            EventData data = event.getData();
            if (data instanceof TableMapEventData) {
                TableMapEventData tableMapEventData = (TableMapEventData) data;
                // 3.1 表映射 不是系统表变更，且tableIdMap中尚不存在，添加id到表名的映射
                if (tableMapEventData.getDatabase().equals(Constants.SYS_NAME)
                        && !tableMapEventData.getTable().startsWith(SYS_TABLE_PREFIX)
                        && tableIdMap.getIfPresent(tableMapEventData.getTableId()) == null) {
                    tableIdMap.put(tableMapEventData.getTableId(), tableMapEventData.getTable());
                    logger.info("日志监控新增id和表名对应，tableId={},tableName={}", tableMapEventData.getTableId(), tableMapEventData.getTable());
                }
            } else if (data instanceof DeleteRowsEventData) {
                DeleteRowsEventData deleteRowsEventData = (DeleteRowsEventData) data;
                // 3.2 监听删除事件
                long tableId = deleteRowsEventData.getTableId();
                if (tableIdMap.asMap().containsKey(tableId)) {
                    // 3.2.1 获取当前tableId对应的计数器
                    TimerCounter timerCounter = deleteCountMap.get(tableId);
                    assert timerCounter != null;
                    // 3.2.2 尝试自增
                    if (!timerCounter.counterInc()) {
                        // 自增失败
                        String tableName = tableIdMap.getIfPresent(tableId);
                        logger.warn("表单出现短时间内大量删除操作，tableName={}", tableName);
                        // 落库
                        tableMonitorService.insertMuchDeleteAlarm(tableName);
                    }
                }
            }
        });

        // 4.尝试连接
        try {
            client.connect();
        } catch (IOException e) {
            throw new ServiceException(ServiceExceptionEnum.LOG_MONITOR_CONNECT_ERROR);
        }

    }


}
