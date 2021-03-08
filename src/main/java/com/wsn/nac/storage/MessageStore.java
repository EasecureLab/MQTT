package com.wsn.nac.storage;

import com.wsn.nac.publish.entity.electricMeter;
import com.wsn.nac.publish.entity.leakage;
import com.wsn.nac.publish.entity.smoke;
import com.wsn.nac.publish.entity.temperature;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * UTF-8
 * Created by czy  Time : 2021/3/3 10:12
 *
 * @version 1.0
 */
@Service
public class MessageStore {

    @Resource(name="deviceHistory")
    MongoTemplate mongoTemplate;

    public void storeElectricMeter(electricMeter electricMeter){
        mongoTemplate.save(electricMeter,"electricMeter");

    }
    public void storeLeakage(leakage readValue) {
        mongoTemplate.save(readValue,"leakage");
    }

    public void storeTemperature(temperature readValue) {
        mongoTemplate.save(readValue,"temperature");
    }

    public void storeSmoke(smoke readValue) {
        mongoTemplate.save(readValue,"smoke");
    }
    public void storeByCollectionId(SensorMessage message,String collectionId) {
        mongoTemplate.save(message,collectionId);
    }
}
