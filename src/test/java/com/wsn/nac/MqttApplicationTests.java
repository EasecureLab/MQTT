package com.wsn.nac;


import com.wsn.nac.Util.TimeFormatTransUtils;
import com.wsn.nac.storage.MessageStore;
import com.wsn.nac.storage.ScheduleTask;
import com.wsn.nac.storage.entity.Sensor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MqttApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Autowired
    ScheduleTask scheduleTask;

    @Autowired
    MessageStore messageStore;

    @Resource
    MongoTemplate mongoTemplateForDeviceHistory;

    @Test
    public void test(){
        // scheduleTask.removeHistory();

        // // scheduleTask.removeHistory();
        // LocalDateTime time = LocalDateTime.now(ZoneOffset.UTC);
        // System.out.println(time);
        // ZonedDateTime utc = time.atZone(ZoneId.of("UTC"));
        // System.out.println(utc);
        // long l = utc.toInstant().toEpochMilli();
        // System.out.println(l);
        //
        // // System.out.println(time);
        // // // time.
        // // Long timeLong = TimeFormatTransUtils.localDateTime2timeStamp(time);
        // // System.out.println(timeLong);
        // // Query query = new Query(Criteria.where("dateTime").lt(timeLong));
        // // mongoTemplateForDeviceHistory.remove(query, Sensor.class,"screen1");

    }
}
