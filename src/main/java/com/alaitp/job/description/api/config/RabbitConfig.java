package com.alaitp.job.description.api.config;

import lombok.Getter;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Getter
@Configuration
public class RabbitConfig {
    @Value("${job.queue}")
    private String jobTopic;
    @Value("${job.exchange}")
    private String jobExchange;
    @Value("${job.key}")
    private String jobKey;

    @Bean
    public DirectExchange jobExchange() {
        return new DirectExchange(jobExchange);
    }

    @Bean
    public Queue jobQueue() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-message-ttl", 10000);
        return new Queue(jobTopic, true, false, false, args);
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(jobQueue()).to(jobExchange()).with(jobKey);
    }
}
