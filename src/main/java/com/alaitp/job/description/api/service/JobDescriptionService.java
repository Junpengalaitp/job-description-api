package com.alaitp.job.description.api.service;

import com.alaitp.job.description.api.entity.JobDescription;

import java.util.List;
import java.util.Map;

public interface JobDescriptionService {

    JobDescription findOneById(String jobId);

    Map<String, Map<String, String>> findJobsByTitle(String jobTitle, String requestId);

    List<JobDescription> JobDescriptionsInDB(String jobTitle);

}
