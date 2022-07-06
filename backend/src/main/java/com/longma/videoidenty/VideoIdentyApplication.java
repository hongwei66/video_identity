package com.longma.videoidenty;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value="${applicationPath:classpath:application.properties}", encoding="UTF-8")
@Slf4j
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class VideoIdentyApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(VideoIdentyApplication.class, args);
        String[] activeProfiles = context.getEnvironment().getActiveProfiles();
        log.info("active profile: {}", activeProfiles);
    }

}
