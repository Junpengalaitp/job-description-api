package com.alaitp.job.description.api;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@Slf4j
@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.alaitp.job.description.api.mapper")
public class JobDescriptionApiApplication {
    public static void main(String[] args) {
        initLogging();
        SpringApplication.run(JobDescriptionApiApplication.class, args);
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
