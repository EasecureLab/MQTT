package com.wsn.nac.storage.mqtt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wsn.nac.entity.electricMeter;
import com.wsn.nac.entity.leakage;
import com.wsn.nac.entity.smoke;
import com.wsn.nac.entity.temperature;
import com.wsn.nac.storage.MessageStore;
import com.wsn.nac.storage.SensorMessage;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * UTF-8
 * Created by czy  Time : 2021/3/3 9:58
 *
 * @version 1.0
 */
@Component
public class MqttRecieve implements MqttCallback {
    @Autowired
    MessageStore messageStore;
    @Override
    public void connectionLost(Throwable throwable) {

    }

    @Override
    public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
        String message=new String(mqttMessage.getPayload(), StandardCharsets.UTF_8);
        messageStore.store(new ObjectMapper().readValue(message, SensorMessage.class));
//        if ("electricMeter".equals(topic)){
//            messageStore.storeElectricMeter(new ObjectMapper().readValue(message,electricMeter.class));
//        }else if ("leakage".equals(topic)){
//            messageStore.storeLeakage(new ObjectMapper().readValue(message, leakage.class));
//        }else if ("temperature".equals(topic)){
//            messageStore.storeTemperature(new ObjectMapper().readValue(message, temperature.class));
//        }else if ("smoke".equals(topic)){
//            messageStore.storeSmoke(new ObjectMapper().readValue(message, smoke.class));
//        }
        System.out.println("messageArrived() topic: "+topic+", message is "+new String(mqttMessage.getPayload(), StandardCharsets.UTF_8));

    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

    }
}
