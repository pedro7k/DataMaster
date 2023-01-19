package com.pedro.domain.support.check;

import com.pedro.common.enums.RuleTypeEnum;
import com.pedro.common.enums.ServiceExceptionEnum;
import com.pedro.common.exceptions.ServiceException;
import javafx.scene.chart.ValueAxis;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ValueCheckUtil {

    private static final Logger logger = LoggerFactory.getLogger(ValueCheckUtil.class);

    /**
     * 数值范围校验
     */
    private static final String VALUE_RANGE = "VALUE_RANGE";

    /**
     * 出现次数校验
     */
    private static final String APPEAR_TIMES = "APPEAR_TIMES";

    /**
     * 数值比例范围校验
     */
    private static final String APPEAR_RATIO = "APPEAR_RATIO";

    /**
     * 检查value是否满足约束
     * 通过则返回null，否则返回当前值value
     *
     * @param type        约束类型
     * @param values      具体值
     * @param valueAppear 值
     * @param valueRange  值范围
     * @param appearTimes 出现次数
     * @param appearRatio 出现次数比例范围
     * @return
     */
    public static Double checkConstrainedValue(int type, List<String> values, String valueAppear, String valueRange, String appearTimes, String appearRatio) {

        // 分类处理
        if (type == RuleTypeEnum.NULL_PERCENT_RULE.getType()) {
            return checkNullPercentValue(values, appearRatio);
        } else if (type == RuleTypeEnum.VALUE_APPEAR_TIMES_RESTRICTION.getType()) {
            return checkValueAppearTimes(values, valueAppear, appearTimes);
        } else if (type == RuleTypeEnum.VALUE_APPEAR_RATIO_RESTRICTION.getType()) {
            return checkValueAppearRatio(values, valueAppear, appearRatio);
        } else if (type == RuleTypeEnum.RANGE_APPEAR_TIMES_RESTRICTION.getType()) {
            return checkRangeAppearTimes(values, valueRange, appearTimes);
        } else if (type == RuleTypeEnum.RANGE_APPEAR_RATIO_RESTRICTION.getType()) {
            return checkRangeAppearRatio(values, valueRange, appearRatio);
        }

        // 异常
        logger.error("扫描时发现数据库中存储了错误的约束类型，RuleType={}", type);
        throw new ServiceException(ServiceExceptionEnum.STORAGE_RULE_TYPE_ERROR);
    }

    /**
     * 检查空缺率
     *
     * @param values
     * @param appearRatio
     * @return
     */
    private static Double checkNullPercentValue(List<String> values, String appearRatio) {

        // 1.获取边界
        Double[] boundary = RuleCheckUtil.getBoundary(appearRatio, APPEAR_RATIO);

        // 2.统计空的值数量，计算空缺率
        double emptyCount = 0;
        for (String value : values) {
            if (StringUtils.isBlank(value)) {
                emptyCount++;
            }
        }
        double nullPercent = emptyCount / values.size();

        // 3.返回
        if (nullPercent >= boundary[0] && nullPercent <= boundary[1]){
            return null;
        }
        return nullPercent;
    }

    /**
     * 检查值出现次数
     *
     * @param values
     * @param valueAppear
     * @param appearTimes
     * @return
     */
    private static Double checkValueAppearTimes(List<String> values, String valueAppear, String appearTimes) {

        // 1.获取边界
        Double[] appearTimesBoundary = RuleCheckUtil.getBoundary(appearTimes, APPEAR_TIMES);

        // 2.统计目标值数量，计算空缺率
        double valueCount = 0;
        for (String value : values) {
            if (value.equals(valueAppear)) {
                valueCount++;
            }
        }

        // 3.返回
        if (valueCount >= appearTimesBoundary[0] && valueCount <= appearTimesBoundary[1]){
            return null;
        }
        return valueCount;

    }

    /**
     * 检查值出现比率
     * @param values
     * @param valueAppear
     * @param appearRatio
     * @return
     */
    private static Double checkValueAppearRatio(List<String> values, String valueAppear, String appearRatio) {

        // 1.获取边界
        Double[] appearRatioBoundary = RuleCheckUtil.getBoundary(appearRatio, APPEAR_RATIO);

        // 2.统计目标值数量，计算所占比率
        double valueCount = 0;
        for (String value : values) {
            if (value.equals(valueAppear)) {
                valueCount++;
            }
        }
        double valueAppearRatio = valueCount / values.size();

        // 3.返回
        if (valueAppearRatio >= appearRatioBoundary[0] && valueAppearRatio <= appearRatioBoundary[1]){
            return null;
        }
        return valueAppearRatio;

    }

    /**
     * 检查范围内值出现次数
     * @param values
     * @param valueRange
     * @param appearTimes
     * @return
     */
    private static Double checkRangeAppearTimes(List<String> values, String valueRange, String appearTimes) {

        // 1.获取边界
        Double[] valueRangeBoundary = RuleCheckUtil.getBoundary(valueRange, VALUE_RANGE);
        Double[] appearTimesBoundary = RuleCheckUtil.getBoundary(appearTimes, APPEAR_TIMES);

        // 2.统计目标值数量，计算在范围内的次数
        double valueCount = 0;
        for (String value : values) {
            Double numValue = Double.parseDouble(value);
            if (numValue >= valueRangeBoundary[0] && numValue <= valueRangeBoundary[1]) {
                valueCount++;
            }
        }

        // 3.返回
        if (valueCount >= appearTimesBoundary[0] && valueCount <= appearTimesBoundary[1]){
            return null;
        }
        return valueCount;
    }

    /**
     * 检查范围内值出现比例
     * @param values
     * @param valueRange
     * @param appearRatio
     * @return
     */
    private static Double checkRangeAppearRatio(List<String> values, String valueRange, String appearRatio) {

        // 1.获取边界
        Double[] valueRangeBoundary = RuleCheckUtil.getBoundary(valueRange, VALUE_RANGE);
        Double[] appearRatioBoundary = RuleCheckUtil.getBoundary(appearRatio, APPEAR_RATIO);

        // 2.统计目标值数量，计算在范围内的次数
        double valueCount = 0;
        for (String value : values) {
            Double numValue = Double.parseDouble(value);
            if (numValue >= valueRangeBoundary[0] && numValue <= valueRangeBoundary[1]) {
                valueCount++;
            }
        }
        double valueAppearRatio = valueCount/values.size();

        // 3.返回
        if (valueAppearRatio >= appearRatioBoundary[0] && valueAppearRatio <= appearRatioBoundary[1]){
            return null;
        }
        return valueAppearRatio;

    }
}
