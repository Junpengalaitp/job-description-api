package com.alaitp.job.description.api.service.impl;

import com.alaitp.job.description.api.entity.JobDescription;
import com.alaitp.job.description.api.mapper.DiceJobDAO;
import com.alaitp.job.description.api.service.JobDescriptionService;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class JobDescriptionServiceImpl implements JobDescriptionService {

    @Autowired
    private DiceJobDAO diceJobDAO;

    /**
     * find job descriptions in sql database by job title, and set request id for each job
     */
    @Override
    public List<JobDescription> findJobsByTitle(@NonNull String jobTitle, String requestId) {
        List<JobDescription> jobDescriptionList = diceJobDAO.findJobDescriptionsByJobTitle(jobTitle);
        log.info("selected {} jobs with title: {} from SQL", jobDescriptionList.size(), jobTitle);
        jobDescriptionList.forEach((JobDescription jobDescription) -> jobDescription.setRequestId(requestId));
        return jobDescriptionList;
    }
}
