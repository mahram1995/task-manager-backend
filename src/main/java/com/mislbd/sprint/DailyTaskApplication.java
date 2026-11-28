package com.mislbd.sprint;

import com.mislbd.sprint.serviceImpl.AuthServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class DailyTaskApplication extends SpringBootServletInitializer {

    private final AuthServiceImpl service;

    public DailyTaskApplication(AuthServiceImpl service) {
        this.service = service;
    }

    public static void main(String[] args) {

        SpringApplication.run(DailyTaskApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(
            SpringApplicationBuilder application) {

        return application.sources(
                DailyTaskApplication.class
        );
    }


}
