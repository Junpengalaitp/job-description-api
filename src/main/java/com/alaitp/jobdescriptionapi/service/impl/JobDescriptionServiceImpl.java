package com.alaitp.jobdescriptionapi.service.impl;

import com.alaitp.jobdescriptionapi.entity.JobDescription;
import com.alaitp.jobdescriptionapi.mapper.DiceJobDAO;
import com.alaitp.jobdescriptionapi.service.CachingService;
import com.alaitp.jobdescriptionapi.service.JobDescriptionService;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public Map<String, JobDescription> findJobsByTitle(@NonNull String jobTitle) {
        List<JobDescription> jobDescriptionList = JobDescriptionsInDB(jobTitle);
        Map<String, JobDescription> jobDescriptionMap = new HashMap<>();
        for (JobDescription jobDescription: jobDescriptionList) {
            jobDescriptionMap.put(jobDescription.getJobId(), jobDescription);
        }
        cachingService.cacheJobDescriptions(jobDescriptionMap, jobTitle);
        return jobDescriptionMap;
    }

    /**
     * job descriptions in cache and database
     */
    @Override
    public List<JobDescription> JobDescriptionsInDB(String jobTitle) {
        List<JobDescription> jobDescriptionsCache = cachingService.cachedJobTitle(jobTitle);
        List<String> cachedIds = new ArrayList<>();
        for (var jobDesc: jobDescriptionsCache) {
            cachedIds.add(jobDesc.getJobId());
        }
        List<JobDescription> jobDescriptionSQL;
        if (cachedIds.size() > 0) {
            jobDescriptionSQL = diceJobDAO.notCachedJobDescriptionsByJobTitle(jobTitle, cachedIds);
        } else {
            jobDescriptionSQL = diceJobDAO.findJobDescriptionsByJobTitle(jobTitle);
        }
        log.info("selected {} jobs with title: {} from SQL", jobDescriptionSQL.size(), jobTitle);
        List<JobDescription> jobDescriptionList = new ArrayList<>(jobDescriptionsCache);
        jobDescriptionList.addAll(jobDescriptionSQL);
        return jobDescriptionList;
    }
}
