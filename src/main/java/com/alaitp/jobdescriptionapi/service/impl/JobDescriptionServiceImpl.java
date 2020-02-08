package com.alaitp.jobdescriptionapi.service.impl;

import com.alaitp.jobdescriptionapi.entity.JobDescription;
import com.alaitp.jobdescriptionapi.mapper.DiceJobDAO;
import com.alaitp.jobdescriptionapi.service.JobDescriptionService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class JobDescriptionServiceImpl implements JobDescriptionService {

    @Autowired
    private DiceJobDAO diceJobDAO;

    @Override
    public JobDescription findOneById(@NonNull String jobId) {
        return diceJobDAO.selectDiceByJobId(jobId);
    }

    @Override
    public Map<String, JobDescription> findJobsByTitle(@NonNull String jobTitle) {
        List<JobDescription> jobDescriptionList = diceJobDAO.findJobDescriptionsByJobTitle(jobTitle);
        var jobDescriptionMap = new HashMap<String, JobDescription>();
        for (var jobDescription: jobDescriptionList) {
            jobDescriptionMap.put(jobDescription.getJobId(), jobDescription);
        }
        return jobDescriptionMap;
    }
}
