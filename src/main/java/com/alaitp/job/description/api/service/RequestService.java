package com.alaitp.job.description.api.service;

import com.alaitp.job.description.api.dto.RemotiveJob;

import java.util.Map;

public interface RequestService {
    /**
     * Request jobs from remotive.io
     */
    Map<String, RemotiveJob> searchRemotiveJobs(String searchWord, String requestId);
}
