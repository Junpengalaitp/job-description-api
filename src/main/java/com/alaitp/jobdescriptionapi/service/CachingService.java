package com.alaitp.jobdescriptionapi.service;

import java.util.Map;

public interface CachingService {
    void cacheJobsByRequestId(Map<String, Map<String, String>> jobDescription, String jobTitle);
}
