package com.wsn.nac.config;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClients;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Collections;


/**
 * UTF-8
 * Created by czy  Time : 2021/3/3 20:25
 *
 * @version 1.0
 */
@Configuration
@ConfigurationProperties(prefix = "spring.data.mongodb")
@Data
public class MongoDBConfig {

    private String username;
    private String password;
    private String host;
    private String port;
    private String authenticationDatabase;
    private String maxConnectionIdleTime;

//    @Bean("device")
//    @Primary
//    public MongoTemplate mongoTemplateForDevice() {
//        return getMongoTemplateByDatabaseName("easecurelab_device");
//    }

    @Bean
    public MongoTemplate mongoTemplateForDeviceHistory() {
        return getMongoTemplateByDatabaseName("history");
    }

    @Bean
    @Primary
    public MongoTemplate mongoTemplateForDevice() {
        return getMongoTemplateByDatabaseName("device");
    }

//    private MongoTemplate getMongoTemplateByDatabaseName(String databaseName) {
//        String connectionString = "mongodb://"+username+":"+password+"@"+host+":"+port+"/"
//                +authenticationDatabase+"?"+"maxidletimems="+maxConnectionIdleTime;
//
//        return new MongoTemplate(MongoClients.create(connectionString),databaseName);
//    }

    private MongoTemplate getMongoTemplateByDatabaseName(String databaseName) {
        MongoCredential mongoCredential = MongoCredential.createCredential(username, authenticationDatabase,
                password.toCharArray());
        ServerAddress serverAddress = new ServerAddress(host, Integer.parseInt(port));
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder().applyToClusterSettings(builder -> {
            builder.hosts(Collections.singletonList(serverAddress));
        }).credential(mongoCredential).build();
        return new MongoTemplate(MongoClients.create(mongoClientSettings),databaseName);
    }
}
