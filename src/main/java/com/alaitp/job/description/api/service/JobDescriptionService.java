package com.alaitp.job.description.api.service;

import com.alaitp.job.description.api.entity.JobDescription;

import java.util.Collection;

public interface JobDescriptionService {
    /**
     * find job descriptions in sql database by job title, and set request id for each job
     */
    Collection<JobDescription> findJobsByTitle(String jobTitle, int amount, String requestId);

    /**
     * standardize tag, remove duplicated tags, and keep tags max amount at 5
     *
     * @param jobDescription
     */
    void trimTag(JobDescription jobDescription);
}
