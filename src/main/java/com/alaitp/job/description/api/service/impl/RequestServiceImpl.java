package com.alaitp.job.description.api.service.impl;

import com.alaitp.job.description.api.dto.RemotiveJobDto;
import com.alaitp.job.description.api.service.RequestService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import static com.alaitp.job.description.api.constant.ControllerConst.JOBS;
import static com.alaitp.job.description.api.constant.ControllerConst.REMOTIVE_URL;

/**
 * Request jobs from remotive.io
 */
@Service
public class RequestServiceImpl implements RequestService {
    @Override
    public Map<String, RemotiveJobDto> searchRemotiveJobs(String searchWord, String requestId) {
        RestTemplate restTemplate = new RestTemplate();
        String url = REMOTIVE_URL + searchWord;
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        JSONObject resJson = JSON.parseObject(response.getBody());
        JSONArray jobArray = resJson.getJSONArray(JOBS);
        Map<String, RemotiveJobDto> jobDescriptionMap = new HashMap<>();
        for (RemotiveJobDto remotiveJobDto : jobArray.toJavaList(RemotiveJobDto.class)) {
            remotiveJobDto.setRequestId(requestId);
            jobDescriptionMap.put("remotive_" + remotiveJobDto.getId(), remotiveJobDto);
        }
        return jobDescriptionMap;
    }
}
