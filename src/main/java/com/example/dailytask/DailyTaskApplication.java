package com.example.dailytask;

import com.example.dailytask.service.AuthService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class DailyTaskApplication extends SpringBootServletInitializer {

    private final AuthService service;

    public DailyTaskApplication(AuthService service) {
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
