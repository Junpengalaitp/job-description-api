package com.alaitp.job.description.api;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.alaitp.job.description.api.mapper")
public class JobDescriptionApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(JobDescriptionApiApplication.class, args);
    }
}
