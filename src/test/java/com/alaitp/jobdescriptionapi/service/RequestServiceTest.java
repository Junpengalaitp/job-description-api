package com.alaitp.jobdescriptionapi.service;

import com.alaitp.jobdescriptionapi.BaseJunitTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class RequestServiceTest extends BaseJunitTest {

    @Autowired
    private RequestService requestService;

    @Test
    void requestRemotive() {
        requestService.requestRemotive();
    }
}