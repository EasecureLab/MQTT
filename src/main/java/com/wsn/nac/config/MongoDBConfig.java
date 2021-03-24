package com.wsn.nac.config;

import com.mongodb.ConnectionString;
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
    private String maxConnectionIdleTime;

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

    public String getMaxConnectionIdleTime() {
        return maxConnectionIdleTime;
    }

    public void setMaxConnectionIdleTime(String maxConnectionIdleTime) {
        this.maxConnectionIdleTime = maxConnectionIdleTime;
    }

    @Bean("device")
    @Primary
    public MongoTemplate mongoTemplateForDevice() {
        return getMongoTemplateByDatabaseName("easecurelab_device");
    }

    @Bean("deviceHistory")
    public MongoTemplate mongoTemplateForDeviceHistory() {
        return getMongoTemplateByDatabaseName("mqtt_history");
    }

    private MongoTemplate getMongoTemplateByDatabaseName(String databaseName) {
        String connectionString = "mongodb://"+username+":"+password+"@"+host+":"+port+"/"
                +authenticationDatabase+"?"+"maxidletimems="+maxConnectionIdleTime;

        return new MongoTemplate(MongoClients.create(connectionString),databaseName);
    }
}
