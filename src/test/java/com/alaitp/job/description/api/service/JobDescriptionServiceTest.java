package com.alaitp.job.description.api.service;

import com.alaitp.job.description.api.BaseJunitTest;
import com.alaitp.job.description.api.entity.JobDescription;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class JobDescriptionServiceTest extends BaseJunitTest {

    @Autowired
    private JobDescriptionService jobDescriptionService;

    @Test
    void findDescByJobId() {
        String testId = "0bcae987265927452ccab4324c2619c5294c3b2be742c8189ba40d356bb5673f";
        JobDescription jobDescription = jobDescriptionService.findOneById(testId);
        System.out.println(jobDescription);
        assertEquals(testId, jobDescription.getJobId());
    }

    @Test
    void findJobsByTitle() {
        Map<String, JobDescription> jobDescriptions = jobDescriptionService.findJobsByTitle("software engineer", "testRequestId");
        System.out.println(jobDescriptions);
        assertTrue(jobDescriptions.size() > 1);
    }

    @Test
    void jobDescriptionsInDB() {
        List<JobDescription> jobDescriptionList = jobDescriptionService.jobDescriptionsInDB("devops");
        assertEquals(6, jobDescriptionList.size());
    }
}