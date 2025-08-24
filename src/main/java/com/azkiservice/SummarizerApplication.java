package com.azkiservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class SummarizerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SummarizerApplication.class, args);
    }

}
