package com.wsn.nac.publish.service;

import com.wsn.nac.publish.entity.sensor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class sensorRead {

    @Resource(name="device")
    MongoTemplate mongoTemplateService;


    /**
     * 从“sensor”集合中根据传感器名字查询所有对应的传感器
     * @param name
    */
    public List<sensor> readOneSensor(String name){
        return mongoTemplateService.
                find(new Query(Criteria.where("name").is(name)), sensor.class, "sensor");
    }
}
