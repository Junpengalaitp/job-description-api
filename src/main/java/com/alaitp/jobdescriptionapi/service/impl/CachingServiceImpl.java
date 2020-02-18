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
    public void cacheJobDescriptions(Map<String, JobDescription> jobDescriptionMap, String jobTitle) {
        int cached = 0;
        for (var entry: jobDescriptionMap.entrySet()) {
            if (hashOperations.hasKey(jobTitle, entry.getKey())) {
                continue;
            }
            hashOperations.put(jobTitle, entry.getKey(), entry.getValue());
            cached++;
        }
        log.info("cached {} jobs in for job title: {} in redis", cached, jobTitle);
    }

    @Override
    public List<JobDescription> cachedJobTitle(String jobTitle) {
        List<Object> jobDescriptionCache = hashOperations.values(jobTitle);
        List<JobDescription> jobDescriptionList = new ArrayList<>();
        for (var jobDesc: jobDescriptionCache) {
            jobDescriptionList.add(fromObject(jobDesc, JobDescription.class));
        }
        log.info("selected {} jobs with title: {} from cache", jobDescriptionCache.size(), jobTitle);
        return jobDescriptionList;
    }
}
