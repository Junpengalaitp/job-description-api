package com.alaitp.job.description.api.controller;

import com.alaitp.job.description.api.entity.JobDescription;
import com.alaitp.job.description.api.service.JobDescriptionService;
import com.alaitp.job.description.api.thread.JobTransitionThread;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
public class JobSnapShotController {

    private final JobDescriptionService jobDescriptionService;

    @Autowired
    public JobSnapShotController(JobDescriptionService jobDescriptionService) {
        this.jobDescriptionService = jobDescriptionService;
    }

    @GetMapping(value = "/job-list/{jobTitle}/{requestId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getRemotiveJobs(@PathVariable String jobTitle, @PathVariable String requestId) {
        log.info("received path variable: {}, requestId: {}", jobTitle, requestId);
        Map<String, JobDescription> jobDescriptionMap = jobDescriptionService.findJobsByTitle(jobTitle, requestId);
        log.info("Get job list for job title: {} success, size: {}", jobTitle, jobDescriptionMap.size());
        JobTransitionThread thread = new JobTransitionThread(jobDescriptionMap);
        thread.start();
        return ResponseEntity.ok().body(jobDescriptionMap);
    }
}
