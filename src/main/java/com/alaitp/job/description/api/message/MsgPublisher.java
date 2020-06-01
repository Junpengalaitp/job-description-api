package com.alaitp.job.description.api.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MsgPublisher {
    @Value("${job.exchange}")
    private String jobExchange;
    @Value("${job.key}")
    private String jobKey;
    @Autowired
    private AmqpTemplate amqpTemplate;

    public void sendMsg(String msg) {
        amqpTemplate.convertAndSend(jobExchange, jobKey, msg);
        log.info("sent message: {}", msg);
    }
}
