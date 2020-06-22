package com.alaitp.job.description.api.service;

import com.alaitp.job.description.api.entity.JobDescription;

import java.util.List;
import java.util.Map;

public interface JobDescriptionService {

    Map<String, JobDescription> findJobsByTitle(String jobTitle, String requestId);

    List<JobDescription> jobDescriptionsInDB(String jobTitle);

}
