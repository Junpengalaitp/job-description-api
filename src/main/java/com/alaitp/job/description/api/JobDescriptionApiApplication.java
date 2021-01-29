package com.alaitp.job.description.api;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationContextInitializedEvent;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.EventListener;

import java.lang.management.ManagementFactory;

@Slf4j
@EnableDiscoveryClient
@MapperScan("com.alaitp.job.description.api.dao")
@SpringBootApplication
public class JobDescriptionApiApplication {

    public static ApplicationContext applicationContext;

    public static void main(String[] args) {
        applicationContext = SpringApplication.run(JobDescriptionApiApplication.class, args);
    }

    public static <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void initLogging() {
        String defaultVal = "not specified";
        log.info("** Your OS name is : " + System.getProperty("os.name", defaultVal));
        log.info("** The version of the JVM you are running is : " + System.getProperty("java.version", defaultVal));
        log.info("** Your user home directory is : " + System.getProperty("user.home", defaultVal));
        log.info("** Your JRE installation directory is : " + System.getProperty("java.home", defaultVal));
        log.info("** Amount of Your CPU cores : " + Runtime.getRuntime().availableProcessors());
        log.info("** Amount of Your JVM memory: " + (double) ManagementFactory.getMemoryMXBean().getHeapMemoryUsage().getInit() / 1073741.824 + "MB");
    }
}
