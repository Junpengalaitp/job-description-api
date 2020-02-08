package com.alaitp.jobdescriptionapi.service;

import com.alaitp.jobdescriptionapi.entity.JobDescription;

import java.util.Map;

public interface JobDescriptionService {

    JobDescription findOneById(String jobId);

    Map<String, JobDescription> findJobsByTitle(String jobTitle);

}
