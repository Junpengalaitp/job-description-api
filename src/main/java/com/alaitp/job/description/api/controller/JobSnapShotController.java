package com.alaitp.job.description.api.controller;

import com.alaitp.job.description.api.entity.JobDescription;
import com.alaitp.job.description.api.service.JobDescriptionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Api("JobSnapShot")
@Slf4j
@RestController
public class JobSnapShotController {

    private final JobDescriptionService jobDescriptionService;

    @Autowired
    public JobSnapShotController(JobDescriptionService jobDescriptionService) {
        this.jobDescriptionService = jobDescriptionService;
    }

    @ApiOperation(value = "jobSnapshot", notes="get job snapshot by job id")
    @ApiImplicitParam(name = "jobId", value = "jobId", paramType = "string", required = true, dataType = "String")
    @GetMapping(value = "/job-snapshot/{jobId}", produces = "application/json")
    public ResponseEntity<?> jobSnapshot(@PathVariable String jobId) {
        log.info("received path variable: {}", jobId);
        JobDescription jobDesc = jobDescriptionService.findOneById(jobId);
        log.info("Get job id: {} success", jobId);
        return ResponseEntity.ok().body(jobDesc);
    }

    @GetMapping(value = "/job-list/{jobTitle}/{requestId}", produces = "application/json")
    public ResponseEntity<?> jobList(@PathVariable String jobTitle, @PathVariable String requestId) {
        log.info("received path variable: {}, requestId: {}", jobTitle, requestId);
        Map<String, Map<String, String>> jobDescriptionMap = jobDescriptionService.findJobsByTitle(jobTitle, requestId);
        log.info("Get job list for job title: {} success, size: {}", jobTitle, jobDescriptionMap.size());
        return ResponseEntity.ok().body(jobDescriptionMap);
    }
}
