package com.alaitp.jobdescriptionapi.service.impl;

import com.alaitp.jobdescriptionapi.dto.RemotiveJob;
import com.alaitp.jobdescriptionapi.service.RequestService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static com.alaitp.jobdescriptionapi.config.Constants.JOBS;
import static com.alaitp.jobdescriptionapi.config.Constants.REMOTIVE_URL;

@Service
public class RequestServiceImpl implements RequestService {

    @Override
    public void searchRequestRemotive(String searchWord) {
        RestTemplate restTemplate = new RestTemplate();
        String url = REMOTIVE_URL + searchWord;
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        JSONObject resJson = JSON.parseObject(response.getBody());
        JSONArray jobArray = resJson.getJSONArray(JOBS);
        for (RemotiveJob remotiveJob: jobArray.toJavaList(RemotiveJob.class)) {
            System.out.println(remotiveJob.getTitle());
        }
    }
}
