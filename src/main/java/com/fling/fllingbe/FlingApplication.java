package com.fling.fllingbe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class FlingApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlingApplication.class, args);
    }
}
