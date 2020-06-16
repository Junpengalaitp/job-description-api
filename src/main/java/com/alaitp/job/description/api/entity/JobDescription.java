package com.alaitp.job.description.api.entity;

import lombok.Data;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Data
public class JobDescription {

    private String jobId;

    private String jobTitle;

    private String company;

    private String tags;

    private String jobDescriptionText;

    public Map<String, String> toNoIdMap(String requestId) {
        Map<String, String> jobDescMap = new ConcurrentHashMap<>(); // avoid concurrent exception in controller
        jobDescMap.put("requestId", requestId);
        jobDescMap.put("jobTitle", jobTitle);
        jobDescMap.put("company", company);
        jobDescMap.put("tags", String.join(",", tags));
        jobDescMap.put("jobDescriptionText", jobDescriptionText);
        return jobDescMap;
    }
}
