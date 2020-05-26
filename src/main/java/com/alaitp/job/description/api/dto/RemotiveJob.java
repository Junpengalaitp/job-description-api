package com.alaitp.job.description.api.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.jsoup.Jsoup;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Data
public class RemotiveJob extends BaseJobDto {
    private List<String> tags;
    private String jobType;
    private String publicationDate;
    private String candidateRequiredLocation;
    private String salary;
    private String companyLogoUrl;

    public Map<String, String> toNoIdMap() {
        Map<String, String> jobDescMap = new HashMap<>();
        jobDescMap.put("jobTitle", title);
        jobDescMap.put("company", companyName);
        jobDescMap.put("tags", String.join(",", tags));
        jobDescMap.put("jobDescriptionText", getJobDescriptionText());
        return jobDescMap;
    }

    public String getJobDescriptionText() {
        return Jsoup.parse(description).text();
    }
}
