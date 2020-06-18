package com.alaitp.job.description.api.service.impl;

import com.alaitp.job.description.api.entity.JobDescription;
import com.alaitp.job.description.api.mapper.DiceJobDAO;
import com.alaitp.job.description.api.service.JobDescriptionService;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Log4j2
public class JobDescriptionServiceImpl implements JobDescriptionService {

    @Autowired
    private DiceJobDAO diceJobDAO;

    @Override
    public JobDescription findOneById(@NonNull String jobId) {
        return diceJobDAO.selectDiceByJobId(jobId);
    }

    @Override
    public Map<String, JobDescription> findJobsByTitle(@NonNull String jobTitle, String requestId) {
        List<JobDescription> jobDescriptionList = jobDescriptionsInDB(jobTitle);
        Map<String, JobDescription> jobDescriptionMap = new HashMap<>();
        for (JobDescription jobDescription: jobDescriptionList) {
            jobDescription.setRequestId(requestId);
            jobDescriptionMap.put(jobDescription.getJobId(), jobDescription);
        }
        return jobDescriptionMap;
    }

    /**
     * job descriptions in database
     */
    @Override
    public List<JobDescription> jobDescriptionsInDB(String jobTitle) {
        List<JobDescription> jobDescriptionSQL = diceJobDAO.findJobDescriptionsByJobTitle(jobTitle);
        log.info("selected {} jobs with title: {} from SQL", jobDescriptionSQL.size(), jobTitle);
        return jobDescriptionSQL;
    }
}
