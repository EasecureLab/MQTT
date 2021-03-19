package com.wsn.nac;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableScheduling
@EnableSwagger2Doc
@EnableDiscoveryClient
@SpringBootApplication
public class NacApplication {

    public static void main(String[] args) {
        SpringApplication.run(NacApplication.class, args);
    }

}
