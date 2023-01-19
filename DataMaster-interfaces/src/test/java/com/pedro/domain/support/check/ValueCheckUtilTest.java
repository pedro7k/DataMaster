package com.pedro.domain.support.check;

import com.pedro.common.enums.RuleTypeEnum;
import junit.framework.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class ValueCheckUtilTest {

    @Test
    public void testCheckNullPercentValue(){

        List<String> values = new ArrayList<>();
        values.add("test");
        values.add("true");
        values.add(null);
        String appearRatio = "0-0.5";

//        Assert.assertFalse(ValueCheckUtil.checkConstrainedValue(RuleTypeEnum.NULL_PERCENT_RULE.getType(), values, null, null, null,appearRatio));

    }
}
