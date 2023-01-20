package com.pedro.domain.score.service.impl;

import com.pedro.domain.score.service.ScoreClearService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class ScoreClearServiceImplTest {

    @Resource
    private ScoreClearService scoreClearService;

    @Test
    public void testDeleteScoreInPastOneHour(){
        scoreClearService.deleteScoreInPastOneHour();
    }
}
