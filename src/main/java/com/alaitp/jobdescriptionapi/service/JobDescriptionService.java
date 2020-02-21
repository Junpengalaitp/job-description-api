package com.alaitp.jobdescriptionapi.service;

import com.alaitp.jobdescriptionapi.entity.JobDescription;

import java.util.List;
import java.util.Map;

public interface JobDescriptionService {

    JobDescription findOneById(String jobId);

    Map<String, Map<String, String>> findJobsByTitle(String jobTitle);

    List<JobDescription> JobDescriptionsInDB(String jobTitle);

}
