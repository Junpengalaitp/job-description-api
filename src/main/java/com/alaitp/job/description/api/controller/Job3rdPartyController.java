package com.alaitp.job.description.api.controller;

import com.alaitp.job.description.api.dto.RemotiveJob;
import com.alaitp.job.description.api.service.RequestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
public class Job3rdPartyController {

    @Autowired
    private RequestService requestService;

//    @GetMapping(value = "/job-list/{jobTitle}/{requestId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getRemotiveJobs(@PathVariable String jobTitle, @PathVariable String requestId) {
        log.info("received path variable: {}, requestId: {}", jobTitle, requestId);
        Map<String, RemotiveJob> jobDescriptionMap = requestService.searchRemotiveJobs(jobTitle, requestId);
        log.info("Get job list for job title: {} success, size: {}", jobTitle, jobDescriptionMap.size());
//        JobTransitionThread thread = new JobTransitionThread(jobDescriptionMap);
//        thread.start();
        return ResponseEntity.ok().body(jobDescriptionMap);
    }
}
