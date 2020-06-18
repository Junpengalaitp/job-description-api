package com.alaitp.job.description.api.Thread;

import com.alaitp.job.description.api.ApplicationContextProvider;
import com.alaitp.job.description.api.entity.JobDescription;
import com.alaitp.job.description.api.message.MsgPublisher;
import com.alibaba.fastjson.JSON;

import java.util.Map;

public class JobTransitionThread extends Thread {

    private final MsgPublisher msgPublisher = ApplicationContextProvider.getBean(MsgPublisher.class);

    private final Map<String, JobDescription> jobMap;

    public JobTransitionThread(Map<String, JobDescription> jobMap) {
        this.jobMap = jobMap;
    }

    @Override
    public void run() {
        for (var jobDescription: jobMap.values()) {
            msgPublisher.sendMsg(JSON.toJSONString(jobDescription));
        }
    }
}
