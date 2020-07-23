package com.alaitp.job.description.api.thread;

import com.alaitp.job.description.api.entity.JobDescription;
import com.alaitp.job.description.api.message.MsgPublisher;
import com.alaitp.job.description.api.util.ApplicationContextProvider;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * Send jobs for an request by MQ, with additional info
 */
public class JobTransitionThread implements Runnable {

    private final MsgPublisher msgPublisher = ApplicationContextProvider.getBean(MsgPublisher.class);

    private final List<JobDescription> jobDescriptionList;

    public JobTransitionThread(List<JobDescription> jobDescriptionList) {
        this.jobDescriptionList = jobDescriptionList;
    }

    @Override
    public void run() {
        int totalJobs = jobDescriptionList.size();
        for (int i = 0; i < totalJobs; i++) {
            JSONObject jobDescriptionJson = JSON.parseObject(JSON.toJSONString(jobDescriptionList.get(i)));
            jobDescriptionJson.put("jobNumber", i + 1);
            jobDescriptionJson.put("totalJobCount", totalJobs);
            jobDescriptionJson.put("requestEnd", i + 1 == totalJobs);
            msgPublisher.sendMsg(jobDescriptionJson.toJSONString());
        }
    }
}
