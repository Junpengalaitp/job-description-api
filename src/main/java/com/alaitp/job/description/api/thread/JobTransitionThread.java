package com.alaitp.job.description.api.thread;

import com.alaitp.job.description.api.ApplicationContextProvider;
import com.alaitp.job.description.api.entity.JobDescription;
import com.alaitp.job.description.api.message.MsgPublisher;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JobTransitionThread extends Thread {

    private final MsgPublisher msgPublisher = ApplicationContextProvider.getBean(MsgPublisher.class);

    private final Map<String, JobDescription> jobMap;

    public JobTransitionThread(Map<String, JobDescription> jobMap) {
        this.jobMap = jobMap;
    }

    @Override
    public void run() {
        List<JobDescription> jobDescriptionList = new ArrayList<>(jobMap.values());
        int totalJobs = jobDescriptionList.size();
        for (int i = 0; i < totalJobs; i++) {
            JSONObject jobDescriptionJson = JSONObject.parseObject(JSON.toJSONString(jobDescriptionList.get(i)));
            jobDescriptionJson.put("jobNumber", i + 1);
            jobDescriptionJson.put("totalJobCount", totalJobs);
            jobDescriptionJson.put("requestEnd", i + 1 == totalJobs);
            msgPublisher.sendMsg(jobDescriptionJson.toJSONString());
        }
    }
}
