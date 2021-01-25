package com.alaitp.job.description.api.thread;

import com.alaitp.job.description.api.JobDescriptionApiApplication;
import com.alaitp.job.description.api.controller.JobController;
import com.alaitp.job.description.api.entity.JobDescription;
import com.alaitp.job.description.api.message.MsgPublisher;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.Callable;

/**
 * Send jobs for an request by MQ, with additional info
 *
 * @see JobController
 */
@Slf4j
public class JobTransitionThread implements Callable<Void> {

    private final MsgPublisher msgPublisher = JobDescriptionApiApplication.getBean(MsgPublisher.class);

    private final List<JobDescription> jobDescriptionList;

    public JobTransitionThread(List<JobDescription> jobDescriptionList) {
        this.jobDescriptionList = jobDescriptionList;
    }

    @Override
    public Void call() {
        int totalJobs = jobDescriptionList.size();
        for (int i = 0; i < totalJobs; i++) {
            JSONObject jobDescriptionJson = JSON.parseObject(JSON.toJSONString(jobDescriptionList.get(i)));
            jobDescriptionJson.put("jobNumber", i + 1);
            jobDescriptionJson.put("totalJobCount", totalJobs);
            jobDescriptionJson.put("requestEnd", i + 1 == totalJobs);
            msgPublisher.sendJobMessage(jobDescriptionJson.toJSONString());
        }
        log.info("{} totalJobs sent to mq", totalJobs);
        return null;
    }
}
