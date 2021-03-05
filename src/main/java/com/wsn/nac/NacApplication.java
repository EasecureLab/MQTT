package com.wsn.nac;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class NacApplication {

    public static void main(String[] args) {
        SpringApplication.run(NacApplication.class, args);
    }

}
