// package com.wsn.nac;
//
//
// import com.fasterxml.jackson.databind.ObjectMapper;
// import com.wsn.nac.Util.TimeFormatTransUtils;
// import com.wsn.nac.storage.MessageStore;
// import com.wsn.nac.storage.ScheduleTask;
// import com.wsn.nac.storage.entity.Sensor;
// import com.wsn.nac.storage.entity.SensorData;
// import org.junit.Test;
// import org.junit.runner.RunWith;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.data.mongodb.core.MongoTemplate;
// import org.springframework.data.mongodb.core.query.Criteria;
// import org.springframework.data.mongodb.core.query.Query;
// import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
// import javax.annotation.Resource;
// import java.time.LocalDateTime;
// import java.time.ZoneId;
// import java.time.ZoneOffset;
// import java.time.ZonedDateTime;
// import java.util.Collections;
// import java.util.HashSet;
// import java.util.List;
// import java.util.Set;
// import java.util.concurrent.*;
//
// @RunWith(SpringJUnit4ClassRunner.class)
// @SpringBootTest
// public class MqttApplicationTests {
//
//     @Test
//     public void contextLoads() {
//     }
//
//     @Autowired
//     ScheduleTask scheduleTask;
//
//     @Autowired
//     MessageStore messageStore;
//
//     @Resource
//     MongoTemplate mongoTemplateForDeviceHistory;
//
//     @Test
//     public void test(){
//         // Sensor sensor = new Sensor();
//         // Sensor test = mongoTemplateForDeviceHistory.insert(sensor, "test");
//         // System.out.println(test.get_id());
//
//         ExecutorService threadPool = new ThreadPoolExecutor(100,
//                 110,2L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10000));
//         CountDownLatch countDownLatch = new CountDownLatch(10000);
//         Set<String> synSet = Collections.synchronizedSet(new HashSet<>());
//
//         for (int i = 0; i < 10000; i++) {
//             threadPool.execute(new Runnable() {
//                 @Override
//                 public void run() {
//                     Sensor sensor = new Sensor();
//                     Sensor test = mongoTemplateForDeviceHistory.insert(sensor, "test");
//                     if (synSet.contains(test.get_id())){
//                         System.out.println("1111");
//                     }
//                     synSet.add(test.get_id());
//                     countDownLatch.countDown();
//                 }
//             });
//         }
//         try {
//             countDownLatch.await();
//         } catch (InterruptedException e) {
//             System.out.println("失败");
//             e.printStackTrace();
//         }
//
//         System.out.println(synSet.size());
//
//
//
//
//         // scheduleTask.removeHistory();
//
//         // // scheduleTask.removeHistory();
//         // LocalDateTime time = LocalDateTime.now(ZoneOffset.UTC);
//         // System.out.println(time);
//         // ZonedDateTime utc = time.atZone(ZoneId.of("UTC"));
//         // System.out.println(utc);
//         // long l = utc.toInstant().toEpochMilli();
//         // System.out.println(l);
//         //
//         // // System.out.println(time);
//         // // // time.
//         // // Long timeLong = TimeFormatTransUtils.localDateTime2timeStamp(time);
//         // // System.out.println(timeLong);
//         // // Query query = new Query(Criteria.where("dateTime").lt(timeLong));
//         // // mongoTemplateForDeviceHistory.remove(query, Sensor.class,"screen1");
//
//     }
// }
