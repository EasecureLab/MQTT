package com.wsn.nac;

// import com.alibaba.fastjson.JSON;
// import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wsn.nac.Util.TimeFormatTransUtils;
import com.wsn.nac.storage.entity.MessageFormat;
import com.wsn.nac.storage.MessageStore;
import com.wsn.nac.storage.entity.SensorData;
import com.wsn.nac.storage.entity.SensorMessage;
import com.wsn.nac.storage.common.ScreenEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class saveTest {

    @Autowired
    MessageStore messageStore;

    @Test
    public void storeTest() throws JsonProcessingException {
        // String message = "{\"type\":\"change\",\"sn\":\"TN001\",\"time\":\"2021-11-19 14:31:45\"," +
        //         "\"data\":{\"C1_D1\":[{\"id\":\"1.1.1\",\"desc\":\"temperature\",\"quality\":\"0\",\"value\":\"141.000\"}," +
        //         "{\"id\":\"1.1.2\",\"desc\":\"temperature\",\"quality\":\"0\",\"value\":\"141.000\"}," +
        //         "{\"id\":\"1.1.3\",\"desc\":\"temperature\",\"quality\":\"0\",\"value\":\"141.000\"}," +
        //         "{\"id\":\"1.1.4\",\"desc\":\"temperature\",\"quality\":\"0\",\"value\":\"141.000\"}," +
        //         "{\"id\":\"1.1.5\",\"desc\":\"temperature\",\"quality\":\"0\",\"value\":\"141.000\"}," +
        //         "{\"id\":\"1.1.6\",\"desc\":\"temperature\",\"quality\":\"0\",\"value\":\"141.000\"}," +
        //         "{\"id\":\"1.1.7\",\"desc\":\"temperature\",\"quality\":\"0\",\"value\":\"141.000\"}," +
        //         "{\"id\":\"1.1.8\",\"desc\":\"temperature\",\"quality\":\"0\",\"value\":\"141.000\"}," +
        //         "{\"id\":\"1.1.9\",\"desc\":\"temperature\",\"quality\":\"0\",\"value\":\"141.000\"}," +
        //         "{\"id\":\"1.1.10\",\"desc\":\"temperature\",\"quality\":\"0\",\"value\":\"141.000\"}," +
        //         "{\"id\":\"1.1.11\",\"desc\":\"temperature\",\"quality\":\"0\",\"value\":\"141.000\"}," +
        //         "{\"id\":\"1.1.12\",\"desc\":\"temperature\",\"quality\":\"0\",\"value\":\"141.000\"}," +
        //         "{\"id\":\"1.1.13\",\"desc\":\"temperature\",\"quality\":\"0\",\"value\":\"141.000\"}," +
        //         "{\"id\":\"1.1.14\",\"desc\":\"temperature\",\"quality\":\"0\",\"value\":\"141.000\"}," +
        //         "{\"id\":\"1.1.15\",\"desc\":\"temperature\",\"quality\":\"0\",\"value\":\"141.000\"}," +
        //         "{\"id\":\"1.1.16\",\"desc\":\"temperature\",\"quality\":\"0\",\"value\":\"141.000\"}]," +
        //         "\"C1_D2\":[{\"id\":\"1.2.1\",\"desc\":\"temperature\",\"quality\":\"0\",\"value\":\"141.000\"}," +
        //         "{\"id\":\"1.2.2\",\"desc\":\"temperature\",\"quality\":\"0\",\"value\":\"141.000\"}," +
        //         "{\"id\":\"1.2.3\",\"desc\":\"temperature\",\"quality\":\"0\",\"value\":\"141.000\"}," +
        //         "{\"id\":\"1.2.4\",\"desc\":\"temperature\",\"quality\":\"0\",\"value\":\"141.000\"}," +
        //         "{\"id\":\"1.2.5\",\"desc\":\"temperature\",\"quality\":\"0\",\"value\":\"141.000\"}," +
        //         "{\"id\":\"1.2.6\",\"desc\":\"temperature\",\"quality\":\"0\",\"value\":\"141.000\"}," +
        //         "{\"id\":\"1.2.7\",\"desc\":\"temperature\",\"quality\":\"0\",\"value\":\"141.000\"}," +
        //         "{\"id\":\"1.2.8\",\"desc\":\"temperature\",\"quality\":\"0\",\"value\":\"141.000\"}," +
        //         "{\"id\":\"1.2.9\",\"desc\":\"temperature\",\"quality\":\"0\",\"value\":\"141.000\"}," +
        //         "{\"id\":\"1.2.10\",\"desc\":\"temperature\",\"quality\":\"0\",\"value\":\"141.000\"}," +
        //         "{\"id\":\"1.2.11\",\"desc\":\"temperature\",\"quality\":\"0\",\"value\":\"141.000\"}," +
        //         "{\"id\":\"1.2.12\",\"desc\":\"temperature\",\"quality\":\"0\",\"value\":\"141.000\"}," +
        //         "{\"id\":\"1.2.13\",\"desc\":\"temperature\",\"quality\":\"0\",\"value\":\"141.000\"}," +
        //         "{\"id\":\"1.2.14\",\"desc\":\"temperature\",\"quality\":\"0\",\"value\":\"141.000\"}," +
        //         "{\"id\":\"1.2.15\",\"desc\":\"temperature\",\"quality\":\"0\",\"value\":\"141.000\"}," +
        //         "{\"id\":\"1.2.16\",\"desc\":\"temperature\",\"quality\":\"0\",\"value\":\"141.000\"}]}}";

        String message = "{\"type\":\"desc\",\"sn\":\"TN001\",\"info\":{}}";

        MessageFormat messageFormat = new ObjectMapper().readValue(message, MessageFormat.class);
        if (messageFormat.getInfo() != null){
            System.out.println("info:" + messageFormat.getInfo());
        }
        System.out.println("data:" + messageFormat.getData());

        if (messageFormat.getData() == null){
            System.out.println("1");
            return;
        }

        // System.out.println("data:" + messageFormat.getData().toString());
        Map<String,List<Object>> readValue = new ObjectMapper().readValue(messageFormat.getData().toString(), Map.class);

        for (List<Object> listObject : readValue.values()) {
            for (Object object : listObject) {
                String jsonObject = new ObjectMapper().writeValueAsString(object);
                SensorData sensorData = new ObjectMapper().readValue(jsonObject, SensorData.class);
                System.out.println(sensorData);
            }
        }


        // for (String key : readValue.keySet()) {
        //     System.out.println(key);
        //     List<Object> listObject = readValue.get(key);
        //     for (Object object : listObject) {
        //         // // 将list中的数据转成json字符串
        //         // String jsonObject= JSON.toJSONString(object);
        //         // //将json转成需要的对象
        //         // SensorData sensorData = JSONObject.parseObject(jsonObject, SensorData.class);
        //         // System.out.println(sensorData);
        //         String jsonObject = new ObjectMapper().writeValueAsString(object);
        //         SensorData sensorData = new ObjectMapper().readValue(jsonObject, SensorData.class);
        //         System.out.println(sensorData);
        //     }
        // }






        // for (JsonNode valueJson : readValue.values()) {
        //     // ObjectMapper valueMapper = new ObjectMapper();
        //     // List<MessageFormat.SensorData> values = valueMapper.readValue(valueJson.toString(), new TypeReference<>() {
        //     // });
        //     // System.out.println(values);
        //     System.out.println(valueJson.toString());
        // }

        // for (String value : readValue.values()) {
        //     ObjectMapper valueMapper = new ObjectMapper();
        //     List<MessageFormat.SensorData> values = valueMapper.readValue(value,new TypeReference<List<MessageFormat.SensorData>>(){});
        //     System.out.println(values);
        // }


        // System.out.println("map" + messageFormat.getData().getMap());
        // for(MessageFormat.SensorData sensorData : messageFormat.getData().getC1_D1()) {
        //     SensorMessage sensorMessage = new SensorMessage();
        //     // 此处需要使用\\进行转义
        //     // '|' '+'  '*' 在使用时都需要添加
        //     String[] positionData = sensorData.getId().split("\\.");
        //     int position = Integer.parseInt(positionData[0]);
        //     int X = Integer.parseInt(positionData[1]);
        //     int Y = Integer.parseInt(positionData[2]);
        //     ScreenEnum screen = ScreenEnum.select(position);
        //     sensorMessage.setX(X);
        //     sensorMessage.setY(Y);
        //     sensorMessage.setDateTime(TimeFormatTransUtils.localDateTime2timeStamp(LocalDateTime.now()));
        //     sensorMessage.setData(sensorData.getValue());
        //     messageStore.storeByCollectionName(sensorMessage, screen.toString() + "Test");
        // }

        // MessageFormat.SensorChannel content = new ObjectMapper().readValue(messageFormat.getData(), MessageFormat.SensorChannel.class);
        // System.out.println(content.getC1_D1());
    }

}
