package com.example.workdemo5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.*"})
public class Workdemo5Application {

    public static void main(String[] args) {
        SpringApplication.run(Workdemo5Application.class, args);
    }

}
