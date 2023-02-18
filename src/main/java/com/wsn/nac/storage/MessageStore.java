package com.wsn.nac.storage;

import com.wsn.nac.Util.TimeFormatTransUtils;
import com.wsn.nac.storage.entity.Sensor;
import com.wsn.nac.storage.entity.SensorMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;

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
    public void storeByCollectionName(SensorMessage message, String collectionName) {
        mongoTemplateForDeviceHistory.save(message,collectionName);
    }

    public void storeByCollectionName(Sensor sensor, String collectionName) {
        mongoTemplateForDeviceHistory.save(sensor,collectionName);
    }

    // 移除 time 之前的数据
    public void removeByCollectionNameAndTime(String collectionName, LocalDateTime time){
        Long timeLong = TimeFormatTransUtils.localDateTime2timeStamp(time);
        Query query = new Query(Criteria.where("dateTime").lt(timeLong));
        mongoTemplateForDeviceHistory.remove(query,collectionName);
    }

}
