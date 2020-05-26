package com.alaitp.job.description.api.service;

import java.util.Map;

public interface RequestService {
    Map<String, Map<String, String>> searchRemotiveJobs(String searchWord, String requestId);
}
