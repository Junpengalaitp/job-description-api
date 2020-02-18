package com.alaitp.jobdescriptionapi.controller;

import com.alaitp.jobdescriptionapi.entity.JobDescription;
import com.alaitp.jobdescriptionapi.service.JobDescriptionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Api("JobSnapShot")
@Log4j2
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

    @GetMapping(value = "/job-list/{jobTitle}", produces = "application/json")
    public ResponseEntity<?> jobList(@PathVariable @NonNull String jobTitle) {
        log.info("received path variable: {}", jobTitle);
        Map<String, JobDescription> jobDescriptionMap = jobDescriptionService.findJobsByTitle(jobTitle);
        log.info("Get job list for job title: {} success, size: {}", jobTitle, jobDescriptionMap.size());
        return ResponseEntity.ok().body(jobDescriptionMap);
    }
}
