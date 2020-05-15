package com.alaitp.job.description.api.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MsgPublisher {
    @Value("${job.queue}")
    private String jobTopic;

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void sendMsg(String msg) {
        amqpTemplate.convertAndSend(jobTopic, msg);
        log.info("sent message: {}", msg);
    }
}
