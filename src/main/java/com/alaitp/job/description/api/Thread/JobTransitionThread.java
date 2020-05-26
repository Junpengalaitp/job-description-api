package com.alaitp.job.description.api.Thread;

import com.alaitp.job.description.api.ApplicationContextProvider;
import com.alaitp.job.description.api.message.MsgPublisher;

import java.util.Map;

public class JobTransitionThread extends Thread {

    private MsgPublisher msgPublisher = ApplicationContextProvider.getBean(MsgPublisher.class);

    private Map<String, Map<String, String>> jobMap;

    public JobTransitionThread(Map<String, Map<String, String>> jobMap) {
        this.jobMap = jobMap;
    }

    @Override
    public void run() {
        for (Map<String, String> job: jobMap.values()) {
            msgPublisher.sendMsg(job.toString());
        }
    }
}
