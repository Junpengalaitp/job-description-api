package com.alaitp.jobdescriptionapi.service.impl;

import com.alaitp.jobdescriptionapi.service.RequestService;
import com.alaitp.jobdescriptionapi.utils.JsonUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class RequestServiceImpl implements RequestService {
    @Override
    public void requestRemotive() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://remotive.io/api/remote-jobs";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        Map<String, Object> jsonMap = JsonUtil.fromJson(response.getBody());
        List<Map<String, Object>> jobList = (List<Map<String, Object>>) jsonMap.get("jobs");
        for (Map<String, Object> job : jobList) {
            System.out.println(job.get("title"));
        }
        System.out.println(jsonMap);
    }
}
