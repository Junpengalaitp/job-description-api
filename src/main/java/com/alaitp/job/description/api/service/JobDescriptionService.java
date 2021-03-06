package com.alaitp.job.description.api.service;

import com.alaitp.job.description.api.entity.JobDescription;

import java.util.List;

public interface JobDescriptionService {
    /**
     * find job descriptions in sql database by job title, and set request id for each job
     */
    List<JobDescription> findJobsByTitle(String jobTitle, int amount, String requestId);

    /**
     * standardize tag, remove duplicated tags, and keep tags max amount at 5
     *
     * @param jobDescription
     */
    void trimTag(JobDescription jobDescription);
}
