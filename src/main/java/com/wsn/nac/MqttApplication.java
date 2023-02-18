package com.wsn.nac;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PostConstruct;
import java.util.TimeZone;


@EnableScheduling
@EnableSwagger2Doc
// @EnableDiscoveryClient
@SpringBootApplication
public class MqttApplication {

    public static void main(String[] args) {
        // 设置运行的console日志的时区
        TimeZone timeZone = TimeZone.getTimeZone("GMT+8");
        TimeZone.setDefault(timeZone);

        SpringApplication.run(MqttApplication.class, args);
    }

}
