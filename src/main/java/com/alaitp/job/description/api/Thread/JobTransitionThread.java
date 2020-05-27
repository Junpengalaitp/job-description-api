package com.alaitp.job.description.api.Thread;

import com.alaitp.job.description.api.ApplicationContextProvider;
import com.alaitp.job.description.api.message.MsgPublisher;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.SneakyThrows;

import java.util.Map;

public class JobTransitionThread extends Thread {

    private final MsgPublisher msgPublisher = ApplicationContextProvider.getBean(MsgPublisher.class);

    private final Map<String, Map<String, String>> jobMap;

    public JobTransitionThread(Map<String, Map<String, String>> jobMap) {
        this.jobMap = jobMap;
    }

    @Override
    public void run() {
        for (var entry: jobMap.entrySet()) {
            String jobId = entry.getKey();
            Map<String, String> job = entry.getValue();
            job.put("jobId", jobId);
            msgPublisher.sendMsg(JSON.toJSONString(job));
        }
    }
}
