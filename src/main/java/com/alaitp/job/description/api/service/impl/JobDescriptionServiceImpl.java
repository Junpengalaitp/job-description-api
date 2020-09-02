package com.alaitp.job.description.api.service.impl;

import com.alaitp.job.description.api.dao.JobDescriptionDao;
import com.alaitp.job.description.api.entity.JobDescription;
import com.alaitp.job.description.api.service.CoOccurrenceService;
import com.alaitp.job.description.api.service.JobDescriptionService;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
public class JobDescriptionServiceImpl implements JobDescriptionService {

    @Autowired
    private JobDescriptionDao jobDescriptionDAO;
    @Autowired
    private CoOccurrenceService coOccurrenceService;

    /**
     * find job descriptions in sql database by job title, and set request id for each job
     */
    @Override
    public List<JobDescription> findJobsByTitle(@NonNull String jobTitle, int amount, String requestId) {
        List<JobDescription> jobDescriptionList = jobDescriptionDAO.findJobDescriptionsByJobTitle(jobTitle, amount);
        log.info("selected {} jobs with title: {} from SQL", jobDescriptionList.size(), jobTitle);
        jobDescriptionList.forEach(d -> {
            d.setRequestId(requestId);
            trimTag(d);
        });
        return jobDescriptionList;
    }

    @Override
    public void trimTag(JobDescription jobDescription) {
        if (StringUtils.isEmpty(jobDescription.getTags())) {
            return;
        }
        Set<String> standardizedTags = new HashSet<>();
        for (String tag : jobDescription.getTags().split(", ")) {
            standardizedTags.add(coOccurrenceService.getStandardWord(tag));
            if (standardizedTags.size() == 5) {
                break;
            }
        }
        jobDescription.setTags(String.join(", ", standardizedTags));
    }

}
