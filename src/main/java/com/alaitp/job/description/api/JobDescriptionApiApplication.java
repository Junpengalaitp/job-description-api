package com.alaitp.job.description.api;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;

@Slf4j
@EnableDiscoveryClient
@MapperScan("com.alaitp.job.description.api.dao")
@SpringBootApplication
public class JobDescriptionApiApplication {

    public static ApplicationContext applicationContext;

    public static void main(String[] args) {
        initLogging();
        applicationContext = SpringApplication.run(JobDescriptionApiApplication.class, args);
    }

    public static <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }

    private static void initLogging() {
        String defaultVal = "not specified";
        String javaVersion = System.getProperty("java.version", defaultVal);
        String osName = System.getProperty("os.name", defaultVal);
        String userHome = System.getProperty("user.home", defaultVal);
        String javaHome = System.getProperty("java.home", defaultVal);
        log.info("** Your OS name is : " + osName);
        log.info("** The version of the JVM you are running is : " + javaVersion);
        log.info("** Your user home directory is : " + userHome);
        log.info("** Your JRE installation directory is : " + javaHome);
    }
}
