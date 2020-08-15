package com.alaitp.job.description.api.service;

import com.alaitp.job.description.api.BaseJunitTest;
import com.alaitp.job.description.api.dto.RemotiveJobDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertTrue;


class RequestServiceTest extends BaseJunitTest {
    @Autowired
    private RequestService requestService;

    @Test
    void requestRemotive() {
        Map<String, RemotiveJobDto> map = requestService.searchRemotiveJobs("Java", "test" + UUID.randomUUID());
        System.out.println(map);
        assertTrue(map.size() > 0);
    }
}