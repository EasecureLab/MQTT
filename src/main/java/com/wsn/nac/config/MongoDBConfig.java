package com.wsn.nac.config;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClients;
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
public class MongoDBConfig {

    private String username;
    private String password;
    private String host;
    private String port;
    private String authenticationDatabase;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getAuthenticationDatabase() {
        return authenticationDatabase;
    }

    public void setAuthenticationDatabase(String authenticationDatabase) {
        this.authenticationDatabase = authenticationDatabase;
    }

    @Bean("device")
    @Primary
    public MongoTemplate mongoTemplateForDevice() {
        return getMongoTemplateByDatabaseName("easecurelab_device");
    }

    @Bean("deviceHistory")
    public MongoTemplate mongoTemplateForDeviceHistory() {
        return getMongoTemplateByDatabaseName("easecurelab_deviceHistory");
    }

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
