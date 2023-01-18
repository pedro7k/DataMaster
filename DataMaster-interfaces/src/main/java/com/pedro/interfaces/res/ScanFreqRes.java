package com.pedro.interfaces.res;

/**
 * 扫描频率返回结果，包装
 */
public class ScanFreqRes {

    int freq;

    public ScanFreqRes() {
    }

    public ScanFreqRes(int freq) {
        this.freq = freq;
    }

    public int getFreq() {
        return freq;
    }

    public void setFreq(int freq) {
        this.freq = freq;
    }
}
