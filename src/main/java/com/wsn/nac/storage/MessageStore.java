package com.wsn.nac.storage;

import com.wsn.nac.entity.electricMeter;
import com.wsn.nac.entity.leakage;
import com.wsn.nac.entity.smoke;
import com.wsn.nac.entity.temperature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

/**
 * UTF-8
 * Created by czy  Time : 2021/3/3 10:12
 *
 * @version 1.0
 */
@Service
public class MessageStore {
    final
    MongoTemplate mongoTemplate;

    public MessageStore(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

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
    public void store(SensorMessage message) {
        mongoTemplate.save(message,"sensor");
    }
}
