package com.alaitp.job.description.api.dto;

import lombok.Data;

@Data
public class BaseJobDto {
    protected String id;
    protected String url;
    protected String title;
    protected String companyName;
    protected String category;
    protected String description;
}
