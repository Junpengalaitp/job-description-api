package com.alaitp.job.description.api.controller;

import org.springframework.stereotype.Component;

@Component
public class JobKeywordRestTemplate {
    String keywordUrl = "http://localhost:5000/keywords";

    public String getKeywords() {
//        RestTemplate restTemplate = new RestTemplate();
//        HttpEntity<> request = new HttpEntity<>(new HashMap<>().put("test", "test"));
//        ResponseEntity<String> responseEntity = restTemplate.getForEntity(keywordUrl, String.class, );
//        return responseEntity.getBody();
        return null;
    }

}
