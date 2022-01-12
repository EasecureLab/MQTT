package com.wsn.nac;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wsn.nac.Util.TimeFormatTransUtils;
import com.wsn.nac.storage.MessageFormat;
import com.wsn.nac.storage.MessageStore;
import com.wsn.nac.storage.SensorMessage;
import com.wsn.nac.storage.common.ScreenEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class saveTest {

    @Autowired
    MessageStore messageStore;

    @Test
    public void storeTest() throws JsonProcessingException {
        String message = "{\"type\":\"change\",\"sn\":\"TN001\",\"time\":\"2021-11-19 14:31:45\"," +
                "\"data\":{\"C1_D1\":[{\"id\":\"1.1.1\",\"desc\":\"temperature\",\"quality\":\"0\",\"value\":\"141.000\"}," +
                "{\"id\":\"1.1.2\",\"desc\":\"temperature\",\"quality\":\"0\",\"value\":\"141.000\"}," +
                "{\"id\":\"1.1.3\",\"desc\":\"temperature\",\"quality\":\"0\",\"value\":\"141.000\"}," +
                "{\"id\":\"1.1.4\",\"desc\":\"temperature\",\"quality\":\"0\",\"value\":\"141.000\"}," +
                "{\"id\":\"1.1.5\",\"desc\":\"temperature\",\"quality\":\"0\",\"value\":\"141.000\"}," +
                "{\"id\":\"1.1.6\",\"desc\":\"temperature\",\"quality\":\"0\",\"value\":\"141.000\"}," +
                "{\"id\":\"1.1.7\",\"desc\":\"temperature\",\"quality\":\"0\",\"value\":\"141.000\"}," +
                "{\"id\":\"1.1.8\",\"desc\":\"temperature\",\"quality\":\"0\",\"value\":\"141.000\"}," +
                "{\"id\":\"1.1.9\",\"desc\":\"temperature\",\"quality\":\"0\",\"value\":\"141.000\"}," +
                "{\"id\":\"1.1.10\",\"desc\":\"temperature\",\"quality\":\"0\",\"value\":\"141.000\"}," +
                "{\"id\":\"1.1.11\",\"desc\":\"temperature\",\"quality\":\"0\",\"value\":\"141.000\"}," +
                "{\"id\":\"1.1.12\",\"desc\":\"temperature\",\"quality\":\"0\",\"value\":\"141.000\"}," +
                "{\"id\":\"1.1.13\",\"desc\":\"temperature\",\"quality\":\"0\",\"value\":\"141.000\"}," +
                "{\"id\":\"1.1.14\",\"desc\":\"temperature\",\"quality\":\"0\",\"value\":\"141.000\"}," +
                "{\"id\":\"1.1.15\",\"desc\":\"temperature\",\"quality\":\"0\",\"value\":\"141.000\"}," +
                "{\"id\":\"1.1.16\",\"desc\":\"temperature\",\"quality\":\"0\",\"value\":\"141.000\"}]," +
                "\"C1_D2\":[{\"id\":\"1.2.1\",\"desc\":\"temperature\",\"quality\":\"0\",\"value\":\"141.000\"}," +
                "{\"id\":\"1.2.2\",\"desc\":\"temperature\",\"quality\":\"0\",\"value\":\"141.000\"}," +
                "{\"id\":\"1.2.3\",\"desc\":\"temperature\",\"quality\":\"0\",\"value\":\"141.000\"}," +
                "{\"id\":\"1.2.4\",\"desc\":\"temperature\",\"quality\":\"0\",\"value\":\"141.000\"}," +
                "{\"id\":\"1.2.5\",\"desc\":\"temperature\",\"quality\":\"0\",\"value\":\"141.000\"}," +
                "{\"id\":\"1.2.6\",\"desc\":\"temperature\",\"quality\":\"0\",\"value\":\"141.000\"}," +
                "{\"id\":\"1.2.7\",\"desc\":\"temperature\",\"quality\":\"0\",\"value\":\"141.000\"}," +
                "{\"id\":\"1.2.8\",\"desc\":\"temperature\",\"quality\":\"0\",\"value\":\"141.000\"}," +
                "{\"id\":\"1.2.9\",\"desc\":\"temperature\",\"quality\":\"0\",\"value\":\"141.000\"}," +
                "{\"id\":\"1.2.10\",\"desc\":\"temperature\",\"quality\":\"0\",\"value\":\"141.000\"}," +
                "{\"id\":\"1.2.11\",\"desc\":\"temperature\",\"quality\":\"0\",\"value\":\"141.000\"}," +
                "{\"id\":\"1.2.12\",\"desc\":\"temperature\",\"quality\":\"0\",\"value\":\"141.000\"}," +
                "{\"id\":\"1.2.13\",\"desc\":\"temperature\",\"quality\":\"0\",\"value\":\"141.000\"}," +
                "{\"id\":\"1.2.14\",\"desc\":\"temperature\",\"quality\":\"0\",\"value\":\"141.000\"}," +
                "{\"id\":\"1.2.15\",\"desc\":\"temperature\",\"quality\":\"0\",\"value\":\"141.000\"}," +
                "{\"id\":\"1.2.16\",\"desc\":\"temperature\",\"quality\":\"0\",\"value\":\"141.000\"}]}}";
        MessageFormat messageFormat = new ObjectMapper().readValue(message, MessageFormat.class);
        for(MessageFormat.SensorData sensorData : messageFormat.getData().getC1_D1()) {
            SensorMessage sensorMessage = new SensorMessage();
            // 此处需要使用\\进行转义
            // '|' '+'  '*' 在使用时都需要添加
            String[] positionData = sensorData.getId().split("\\.");
            int position = Integer.parseInt(positionData[0]);
            int X = Integer.parseInt(positionData[1]);
            int Y = Integer.parseInt(positionData[2]);
            ScreenEnum screen = ScreenEnum.select(position);
            sensorMessage.setX(X);
            sensorMessage.setY(Y);
            sensorMessage.setDateTime(TimeFormatTransUtils.localDateTime2timeStamp(LocalDateTime.now()));
            sensorMessage.setData(sensorData.getValue());
            messageStore.storeByCollectionName(sensorMessage, screen.toString() + "Test");
        }

        // MessageFormat.SensorChannel content = new ObjectMapper().readValue(messageFormat.getData(), MessageFormat.SensorChannel.class);
        // System.out.println(content.getC1_D1());
    }

}
