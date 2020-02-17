package com.alaitp.jobdescriptionapi.service.impl;

import com.alaitp.jobdescriptionapi.service.CachingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Service;

@Service
public class CachingServiceImpl implements CachingService {

    @Autowired
    private HashOperations<String, String, Object> hashOperations;

    @Override
    public void cacheJobDescriptions() {
        hashOperations.put("test3", "test3", "testing message3");
    }
}
