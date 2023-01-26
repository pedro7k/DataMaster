package com.pedro.application.binlogMonitor;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 定时计数器
 * 可以指定时间长度和报警阈值
 * 如果在指定时长内，计数超过了报警阈值，则会返回错误
 */
public class TimerCounter {

    /**
     * 单位为秒的时间长度
     */
    int timeLengthWithSec;

    /**
     * 时间长度内允许的计数值
     */
    int alarmThreshold;

    /**
     * 存储时间的计数器
     */
    List<Date> timeList = new LinkedList<>();

    public TimerCounter(int timeLengthWithSec, int alarmThreshold) {
        this.timeLengthWithSec = timeLengthWithSec;
        this.alarmThreshold = alarmThreshold;
    }

    /**
     * 计数自增
     * true成功 false失败
     */
    public synchronized boolean counterInc() {

        // 1.获取当前时间
        Date now = new Date();

        // 2.list内元素数量未达阈值，可以直接添加
        if (timeList.size() < alarmThreshold) {
            timeList.add(now);
            return true;
        }

        // 3.list内元素数量达阈值
        if (DateUtil.between(timeList.get(0), now, DateUnit.SECOND) > timeLengthWithSec) {
            // 3.1 第一个到现在已经超过了时间长度，说明可以淘汰掉第一个
            timeList.remove(0);
            return true;
        }
        // 3.2 第一个到现在还没达到时间长度，说明已经达到阈值，清空list返回失败
        clearCounter();
        return false;
    }

    /**
     * 清空
     */
    public synchronized void clearCounter() {
        timeList.clear();
    }
}
