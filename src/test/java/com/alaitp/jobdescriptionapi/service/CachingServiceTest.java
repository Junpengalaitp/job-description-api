package com.alaitp.jobdescriptionapi.service;

import com.alaitp.job.description.api.service.CachingService;
import com.alaitp.jobdescriptionapi.BaseJunitTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

class CachingServiceTest extends BaseJunitTest {
    @Value("${job.queue}")
    private String topic;

    @Autowired
    private CachingService cachingService;

    @Test
    void test() {
        System.out.println(topic);
    }
}