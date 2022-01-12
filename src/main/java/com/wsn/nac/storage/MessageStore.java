package com.wsn.nac.storage;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * UTF-8
 * Created by czy  Time : 2021/3/3 10:12
 *
 * @version 1.0
 */
@Service
@RequiredArgsConstructor
public class MessageStore {

    @Resource
    MongoTemplate mongoTemplateForDevice;

    @Resource
    MongoTemplate mongoTemplateForDeviceHistory;
//
//    public void storeElectricMeter(electricMeter electricMeter){
//        mongoTemplate.save(electricMeter,"electricMeter");
//
//    }
//    public void storeLeakage(leakage readValue) {
//        mongoTemplate.save(readValue,"leakage");
//    }
//
//    public void storeTemperature(temperature readValue) {
//        mongoTemplate.save(readValue,"temperature");
//    }
//
//    public void storeSmoke(smoke readValue) {
//        mongoTemplate.save(readValue,"smoke");
//    }
    public void storeByCollectionName(SensorMessage message,String collectionName) {
        mongoTemplateForDeviceHistory.save(message,collectionName);
    }

//    public String findSensorByPosition(int position, int X, int Y) {
//
//        return mongoTemplateForDevice.findOne(
//                new Query(Criteria.where("x").is(X).and("y").is(Y).and("position").is(position)), Sensor.class, "sensorTest").getId();
//    }
//    public void readDegree(){}
}
