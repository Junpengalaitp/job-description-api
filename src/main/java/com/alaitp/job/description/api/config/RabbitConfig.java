package com.alaitp.job.description.api.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    @Value("${job.topic}")
    private String jobTopic;

    @Bean
    public Queue queue() {
        return new Queue(jobTopic);
    }
}
