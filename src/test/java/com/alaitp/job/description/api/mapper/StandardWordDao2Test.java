package com.alaitp.job.description.api.mapper;

import com.alaitp.job.description.api.BaseJunitTest;
import com.alaitp.job.description.api.dao.StandardWordDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StandardWordDao2Test extends BaseJunitTest {

    @Autowired
    private StandardWordDao standardWordDao;

    @Test
    void selectStandardWord() {
        String word = "js";
        String standardWord = standardWordDao.selectStandardWord(word);
        System.out.println(standardWord);
        assertEquals("JavaScript", standardWord);
    }
}