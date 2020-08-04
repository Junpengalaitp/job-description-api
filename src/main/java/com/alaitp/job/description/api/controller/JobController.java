package com.alaitp.job.description.api.controller;

import com.alaitp.job.description.api.entity.JobDescription;
import com.alaitp.job.description.api.service.JobDescriptionService;
import com.alaitp.job.description.api.thread.JobTransitionThread;
import com.alaitp.job.description.api.thread.JobTransitionThreadPool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@RestController
public class JobController {

    private final JobDescriptionService jobDescriptionService;

    @Autowired
    public JobController(JobDescriptionService jobDescriptionService) {
        this.jobDescriptionService = jobDescriptionService;
    }

    /**
     * Main controller method, for handling job search request.
     *
     * @return job search result map, key: job id, value: JobDescription
     * Also starts a thread for sending jobs to job-keyword app by mq for analysing job keywords.
     * @see JobTransitionThread
     */
    @GetMapping(value = "/job-list/{jobTitle}/{requestId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getRemotiveJobs(@PathVariable String jobTitle, @PathVariable String requestId) {
        log.info("received path variable: {}, requestId: {}", jobTitle, requestId);
        List<JobDescription> jobDescriptionList = jobDescriptionService.findJobsByTitle(jobTitle, requestId);
        log.info("Get job list for job title: {} success, size: {}", jobTitle, jobDescriptionList.size());
        JobTransitionThreadPool.submit(jobDescriptionList);
        return ResponseEntity.ok().body(jobDescriptionList.stream().collect(Collectors.toMap(JobDescription::getJobId, Function.identity())));
    }
}
