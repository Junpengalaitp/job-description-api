package com.alaitp.job.description.api.service;

import com.alaitp.job.description.api.dto.RemotiveJobDto;

import java.util.Map;

public interface RequestService {
    /**
     * Request jobs from remotive.io
     */
    Map<String, RemotiveJobDto> searchRemotiveJobs(String searchWord, String requestId);
}
