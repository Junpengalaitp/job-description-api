package com.alaitp.jobdescriptionapi.service;

import com.alaitp.job.description.api.service.RequestService;
import com.alaitp.jobdescriptionapi.BaseJunitTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;


class RequestServiceTest extends BaseJunitTest {
    @Autowired
    private RequestService requestService;

    @Test
    void requestRemotive() {
        requestService.searchRequestRemotive("Java");
    }
}