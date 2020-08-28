package com.alaitp.job.description.api.service.impl;

import com.alaitp.job.description.api.BaseJunitTest;
import com.alaitp.job.description.api.entity.CoOccurrenceMatrixRow;
import com.alaitp.job.description.api.service.CoOccurrenceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

import static com.alaitp.job.description.api.constant.CategoryConst.ALL_CATEGORY;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CoOccurrenceServiceImplTest extends BaseJunitTest {

    @Autowired
    private CoOccurrenceService coOccurrenceService;

    @Test
    void getTopIndices() {
        Map<String, Map<String, Object>> getTopRelatedWords = coOccurrenceService.getTopRelatedWords("Java", 10, ALL_CATEGORY);
        System.out.println(getTopRelatedWords);
        assertEquals(10, getTopRelatedWords.size());
    }

    @Test
    void getStandardWord() {
        String word = "python";
        String standardWord = coOccurrenceService.getStandardWord(word);
        System.out.println(standardWord);
        assertEquals("Python", standardWord);
    }

    @Test
    void getCoOccurrenceRowByWord() {
        String word = "Hadoop";
        CoOccurrenceMatrixRow res = coOccurrenceService.getCoOccurrenceRowByWord(word);
        System.out.println(res);
    }
}