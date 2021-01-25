package com.alaitp.job.description.api.message;

import com.alaitp.job.description.api.config.RabbitConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Junpeng He
 */
@Slf4j
@Component
public class RabbitPublisher implements MsgPublisher {
    private final RabbitConfig rabbitConfig;
    private final AmqpTemplate amqpTemplate;

    public RabbitPublisher(RabbitConfig rabbitConfig, AmqpTemplate amqpTemplate) {
        this.rabbitConfig = rabbitConfig;
        this.amqpTemplate = amqpTemplate;
    }

    @Override
    public void sendJobMessage(String msg) {
        amqpTemplate.convertAndSend(rabbitConfig.getJobExchange(), rabbitConfig.getJobKey(), msg);
        log.debug("sent message: {}", msg);
    }
}
