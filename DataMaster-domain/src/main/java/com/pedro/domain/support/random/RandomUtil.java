package com.pedro.domain.support.random;

/**
 * 随机决策工具
 */
public class RandomUtil {

    /**
     * 通过实际每秒扫描次数和期望每秒扫描次数进行随机，返回当此是否扫描
     */
    public static boolean randomIfScanByFreq(int realScanFreq, int expectedScanFreqPerMin) {

        double ratio = (double) expectedScanFreqPerMin / realScanFreq;

        return Math.random() < ratio;

    }
}
