package com.alaitp.job.description.api.service.impl;

import com.alaitp.job.description.api.BaseJunitTest;
import com.alaitp.job.description.api.entity.CoOccurrenceWordCount;
import com.alaitp.job.description.api.service.CoOccurrenceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CoOccurrenceServiceImplTest extends BaseJunitTest {

    @Autowired
    private CoOccurrenceService coOccurrenceService;

    @Test
    void getTopIndices() {
        CoOccurrenceWordCount coOccurrenceWordCount = coOccurrenceService.getCoOccurrenceCountByWord("Python");
        List<Integer> topIndices = coOccurrenceService.getTopIndices(coOccurrenceWordCount, 10);
        System.out.println(topIndices);
        assertEquals(11, topIndices.size());
    }
}