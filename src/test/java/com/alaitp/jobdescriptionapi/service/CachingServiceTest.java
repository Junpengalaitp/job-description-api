package com.alaitp.jobdescriptionapi.service;

import com.alaitp.jobdescriptionapi.BaseJunitTest;
import com.alaitp.jobdescriptionapi.entity.JobDescription;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CachingServiceTest extends BaseJunitTest {

    @Autowired
    private CachingService cachingService;

    @Test
    void cacheJobDescriptions() {
//        cachingService.cacheJobDescriptions();
    }
}