package com.alaitp.jobdescriptionapi.service.impl;

import com.alaitp.jobdescriptionapi.entity.JobDescription;
import com.alaitp.jobdescriptionapi.service.CachingService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.alaitp.jobdescriptionapi.utils.JsonUtil.fromObject;

@Service
@Log4j2
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

    @Override
    public List<JobDescription> cachedJobTitle(String jobTitle) {
        jobTitle = jobTitle.toLowerCase();
        Map<String, Object> jobDescriptionCache = hashOperations.entries(jobTitle);
        List<JobDescription> jobDescriptionList = new ArrayList<>();
        for (var entry: jobDescriptionCache.entrySet()) {
            JobDescription jobDescription = fromObject(entry.getValue(), JobDescription.class);
            jobDescription.setJobId(entry.getKey());
            jobDescriptionList.add(jobDescription);
        }
        log.info("selected {} jobs with title: {} from cache", jobDescriptionCache.size(), jobTitle);
        return jobDescriptionList;
    }
}
