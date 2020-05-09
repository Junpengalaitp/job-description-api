package com.alaitp.jobdescriptionapi.service;

import com.alaitp.job.description.api.service.JobDescriptionService;
import com.alaitp.jobdescriptionapi.BaseJunitTest;
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
        Map<String, Map<String, String>> jobDescriptions = jobDescriptionService.findJobsByTitle("software engineer", "testRequestId");
        System.out.println(jobDescriptions);
        assertTrue(jobDescriptions.size() > 1);
    }

    @Test
    void jobDescriptionsInDB() {
        List<JobDescription> jobDescriptionList = jobDescriptionService.JobDescriptionsInDB("devops");
        assertEquals(6, jobDescriptionList.size());
    }
}