package com.alaitp.job.description.api.service.impl;

import com.alaitp.job.description.api.dto.RemotiveJob;
import com.alaitp.job.description.api.message.MsgPublisher;
import com.alaitp.job.description.api.service.RequestService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static com.alaitp.job.description.api.config.Constants.JOBS;
import static com.alaitp.job.description.api.config.Constants.REMOTIVE_URL;

@Service
public class RequestServiceImpl implements RequestService {
    @Autowired
    private MsgPublisher msgPublisher;

    @Override
    public void searchRequestRemotive(String searchWord) {
        RestTemplate restTemplate = new RestTemplate();
        String url = REMOTIVE_URL + searchWord;
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        JSONObject resJson = JSON.parseObject(response.getBody());
        JSONArray jobArray = resJson.getJSONArray(JOBS);
        for (RemotiveJob remotiveJob: jobArray.toJavaList(RemotiveJob.class)) {
            msgPublisher.sendMsg(JSON.toJSONString(remotiveJob));
        }
    }
}
