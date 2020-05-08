package com.alaitp.jobdescriptionapi.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class RemotiveJob extends BaseJobDto {
    private List<String> tags;
    private String jobType;
    private String publicationDate;
    private String candidateRequiredLocation;
    private String salary;
    private String companyLogoUrl;
}
