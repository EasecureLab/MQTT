package com.wsn.nac.storage.mqtt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wsn.nac.Util.TimeFormatTransUtils;
import com.wsn.nac.storage.MessageFormat;
import com.wsn.nac.storage.MessageStore;
import com.wsn.nac.storage.SensorMessage;
import com.wsn.nac.storage.common.ScreenEnum;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

/**
 * UTF-8
 * Created by czy  Time : 2021/3/3 9:58
 *
 * @version 1.0
 */
@Component
public class MqttReceiveCallback implements MqttCallback {



    @Autowired
    MessageStore messageStore;
    @Override
    public void connectionLost(Throwable throwable) {

    }

    @Override
    public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
        String message=new String(mqttMessage.getPayload(), StandardCharsets.UTF_8);

        // SensorMessage sensorMessage = new ObjectMapper().readValue(message, SensorMessage.class);
//        String collectionId = sensorMessage.getDeviceId();
//        sensorMessage.setId("");
//        messageStore.storeByCollectionId(sensorMessage,collectionId);

//        if ("electricMeter".equals(topic)){
//            messageStore.storeElectricMeter(new ObjectMapper().readValue(message,electricMeter.class));
//        }else if ("leakage".equals(topic)){
//            messageStore.storeLeakage(new ObjectMapper().readValue(message, leakage.class));
//        }else if ("temperature".equals(topic)){
//            messageStore.storeTemperature(new ObjectMapper().readValue(message, temperature.class));
//        }else if ("smoke".equals(topic)){
//            messageStore.storeSmoke(new ObjectMapper().readValue(message, smoke.class));
//        }
        // System.out.println(collectionId);

        System.out.println("订阅的消息内容：");
        System.out.println("messageArrived() topic: "+topic+", message is "+message);
        MessageFormat messageFormat = new ObjectMapper().readValue(message, MessageFormat.class);
        for(MessageFormat.SensorData sensorData : messageFormat.getData().getC1_D1()) {
            SensorMessage sensorMessage = new SensorMessage();
            String[] positionData = sensorData.getId().split("\\.");
            int position = Integer.parseInt(positionData[0]);
            int X = Integer.parseInt(positionData[1]);
            int Y = Integer.parseInt(positionData[2]);
            ScreenEnum screen = ScreenEnum.select(position);
            sensorMessage.setX(X);
            sensorMessage.setY(Y);
            sensorMessage.setDateTime(TimeFormatTransUtils.localDateTime2timeStamp(LocalDateTime.now()));
            sensorMessage.setData(sensorData.getValue());
            messageStore.storeByCollectionName(sensorMessage, screen.toString());
        }


    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

    }
}
