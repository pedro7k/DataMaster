package com.pedro.domain.support.encryption.check;

import org.apache.shiro.crypto.hash.Hash;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RuleCheckUtil {

    /**
     * 数值范围校验
     */
    private static final String VALUE_RANGE = "VALUE_RANGE";
    private static final String EXPRESSION_FOR_VALUE_RANGE = "([\\d]+[\\.][\\d+]|[\\d])[-]([\\d]+[\\.][\\d+]|[\\d])";
    private static final Pattern PATTERN_FOR_VALUE_RANGE = Pattern.compile(EXPRESSION_FOR_VALUE_RANGE);

    /**
     * 出现次数校验
     */
    private static final String APPEAR_TIMES = "APPEAR_TIMES";
    private static final String EXPRESSION_FOR_APPEAR_TIMES = "[\\d]+[-][\\d]+";
    private static final Pattern PATTERN_FOR_APPEAR_TIMES = Pattern.compile(EXPRESSION_FOR_APPEAR_TIMES);

    /**
     * 数值比例范围校验
     */
    private static final String APPEAR_RATIO = "APPEAR_RATIO";
    private static final String EXPRESSION_FOR_APPEAR_RATIO = "(0[\\.][\\d+]|0|1)[-](0[\\.][\\d+]|0|1)";
    private static final Pattern PATTERN_FOR_APPEAR_RATIO = Pattern.compile(EXPRESSION_FOR_APPEAR_RATIO);

    /**
     * 映射map
     */
    private static HashMap<String, String> patternStringMap = new HashMap<>(3);
    private static HashMap<String, Pattern> patternMap = new HashMap<>(3);

    static {
        patternStringMap.put(VALUE_RANGE, EXPRESSION_FOR_VALUE_RANGE);
        patternMap.put(VALUE_RANGE, PATTERN_FOR_VALUE_RANGE);
        patternStringMap.put(APPEAR_TIMES, EXPRESSION_FOR_APPEAR_TIMES);
        patternMap.put(APPEAR_TIMES, PATTERN_FOR_APPEAR_TIMES);
        patternStringMap.put(APPEAR_RATIO, EXPRESSION_FOR_APPEAR_RATIO);
        patternMap.put(APPEAR_RATIO, PATTERN_FOR_APPEAR_RATIO);
    }

    public static boolean checkRuleBoundary(String content, String type) {

        // 1.格式校验
        String patterString = patternStringMap.get(type);
        if (!Pattern.matches(patterString, content)) {
            return false;
        }

        // 2.左右边界校验
        Pattern pattern = patternMap.get(type);
        Matcher matcher = pattern.matcher(content);
        if (matcher.find()) {
            if (Double.parseDouble(matcher.group(1)) <= Double.parseDouble(matcher.group(2))) {
                return true;
            }
        }

        // 3.返回
        return false;
    }


}
