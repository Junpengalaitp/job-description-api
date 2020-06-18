package com.alaitp.job.description.api.entity;

import lombok.Data;

@Data
public class JobDescription {

    private String jobId;

    private String jobTitle;

    private String company;

    private String tags;

    private String jobDescriptionText;

    private String requestId;
}
