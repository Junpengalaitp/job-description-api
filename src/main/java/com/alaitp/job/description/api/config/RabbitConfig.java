package com.alaitp.job.description.api.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
        return new Queue(jobTopic);
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(jobQueue()).to(jobExchange()).with(jobKey);
    }
}
