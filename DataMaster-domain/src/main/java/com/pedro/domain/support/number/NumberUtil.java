package com.pedro.domain.support.number;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class NumberUtil {

    /**
     * Double类型保留指定位数小数
     */
    public static double reserveDoubleScale(double number, int scale){

        // 1.借助BigDecimal转换
        BigDecimal decimal = new BigDecimal(number);
        double result = decimal.setScale(scale, RoundingMode.HALF_UP).doubleValue();

        // 2.返回
        return result;
    }
}
