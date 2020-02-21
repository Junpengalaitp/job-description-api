package com.alaitp.jobdescriptionapi.service;

import com.alaitp.jobdescriptionapi.entity.JobDescription;

import java.util.List;
import java.util.Map;

public interface CachingService {
    void cacheJobDescriptions(Map<String, Map<String, String>> jobDescription, String jobTitle);

    List<JobDescription> cachedJobTitle(String title);
}
