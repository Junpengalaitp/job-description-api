package com.alaitp.job.description.api.service;

import com.alaitp.job.description.api.BaseJunitTest;
import com.alaitp.job.description.api.entity.JobDescription;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class JobDescriptionServiceTest extends BaseJunitTest {

    @Autowired
    private JobDescriptionService jobDescriptionService;

    @Test
    void findJobsByTitle() {
        List<JobDescription> jobDescriptions = jobDescriptionService.findJobsByTitle("python", 1000, "testRequestId");
        System.out.println(jobDescriptions.size());
        assertTrue(jobDescriptions.size() > 1);
    }
}