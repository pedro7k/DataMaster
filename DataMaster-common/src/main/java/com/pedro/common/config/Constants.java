package com.pedro.common.config;

/**
 * 常量定义
 */
public class Constants {

    /**
     * 密码加密的哈希次数
     */
    public final static int HASH_TIMES = 2;

    /**
     * 表和约束的默认权重
     */
    public final static int DEFAULT_WEIGHT = 3;

    /**
     * 单表默认每分钟扫描次数
     */
    public final static int DEFAULT_SCAN_FREQ = 3;

    /**
     * 健康分保留位数
     */
    public final static int HEALTH_SCORE_SCALE = 2;

    /**
     * 表单扫描cron
     */
    public final static String TABLE_SCAN_CRON = "*/5 * * * * ?";

    /**
     * 每分钟的表单扫描次数
     */
    public final static int REAL_SCAN_FREQ_PER_MIN = 12;


}
