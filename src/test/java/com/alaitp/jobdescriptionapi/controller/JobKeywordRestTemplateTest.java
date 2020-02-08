package com.alaitp.jobdescriptionapi.controller;

import com.alaitp.jobdescriptionapi.BaseJunitTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class JobKeywordRestTemplateTest extends BaseJunitTest {
    @Autowired
    private JobKeywordRestTemplate jobKeywordRestTemplate;

    @Test
    void getKeywords() {
        String response = jobKeywordRestTemplate.getKeywords();
        assertNotNull(response);
    }
}