package com.alaitp.jobdescriptionapi.service.impl;

import com.alaitp.jobdescriptionapi.entity.JobDescription;
import com.alaitp.jobdescriptionapi.mapper.DiceJobDAO;
import com.alaitp.jobdescriptionapi.service.CachingService;
import com.alaitp.jobdescriptionapi.service.JobDescriptionService;
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

    @Autowired
    private CachingService cachingService;

    @Override
    public JobDescription findOneById(@NonNull String jobId) {
        return diceJobDAO.selectDiceByJobId(jobId);
    }

    @Override
    public Map<String, Map<String, String>> findJobsByTitle(@NonNull String jobTitle, String requestId) {
        List<JobDescription> jobDescriptionList = JobDescriptionsInDB(jobTitle);
        Map<String, Map<String, String>> jobDescriptionMap = new HashMap<>();
        for (JobDescription jobDescription: jobDescriptionList) {
            jobDescriptionMap.put(jobDescription.getJobId(), jobDescription.toNoIdMap());
        }
        cachingService.cacheJobsByRequestId(jobDescriptionMap, requestId);
        return jobDescriptionMap;
    }

    /**
     * job descriptions in database
     */
    @Override
    public List<JobDescription> JobDescriptionsInDB(String jobTitle) {
        List<JobDescription> jobDescriptionSQL = diceJobDAO.findJobDescriptionsByJobTitle(jobTitle);
        log.info("selected {} jobs with title: {} from SQL", jobDescriptionSQL.size(), jobTitle);
        return jobDescriptionSQL;
    }
}
