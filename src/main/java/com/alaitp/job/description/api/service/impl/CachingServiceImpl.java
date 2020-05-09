package com.alaitp.job.description.api.service.impl;

import com.alaitp.job.description.api.service.CachingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
public class CachingServiceImpl implements CachingService {

    @Autowired
    private HashOperations<String, String, Object> hashOperations;

    @Override
    public void cacheJobsByRequestId(Map<String,  Map<String, String>> jobDescriptionMap, String requestId) {
        int cached = 0;
        requestId = requestId.toLowerCase();
        for (var entry: jobDescriptionMap.entrySet()) {
            if (hashOperations.hasKey(requestId, entry.getKey())) {
                continue;
            }
            hashOperations.put(requestId, entry.getKey(), entry.getValue());
            cached++;
        }
        log.info("cached {} jobs in for request id: {} in redis", cached, requestId);
    }
}
