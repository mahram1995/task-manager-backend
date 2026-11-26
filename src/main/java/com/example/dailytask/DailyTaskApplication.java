package com.example.dailytask;
import com.example.dailytask.service.AuthService;
import org.springframework.boot.SpringApplication; import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication public class DailyTaskApplication {

    private final AuthService service;

    public DailyTaskApplication(AuthService service) {
        this.service = service;
    }

    public static void main(String[] args){

        SpringApplication.run(DailyTaskApplication.class,args);}



}
