package com.wsn.nac.storage;

import com.wsn.nac.storage.mqtt.MqttReceiveCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * UTF-8
 * Created by czy  Time : 2021/3/3 9:50
 *
 * @version 1.0
 */
@Component
public class MqttMessageStoreRunner implements CommandLineRunner {

    @Autowired
    MqttReceiveCallback mqttReceive;

    @Override
    public void run(String... args)  {
        String HOST = "tcp://121.4.39.153:1883";
        String[] TOPICS = {"ElectricMeter","leakage","smoke","temperature"};
        int[] qos = {2,2,2,2};
        String clientId = "javaClient";
        String userName = "wsn";
        String passWord = "wsn405407";
        MqttClient client = null;
        try {
            // host为主机名，test为clientid即连接MQTT的客户端ID，一般以客户端唯一标识符表示，MemoryPersistence设置clientid的保存形式，默认为以内存保存
            client = new MqttClient(HOST, clientId, new MemoryPersistence());
            // MQTT的连接设置
            MqttConnectOptions options = new MqttConnectOptions();

            // 设置是否清空session,这里如果设置为false表示服务器会保留客户端的连接记录，这里设置为true表示每次连接到服务器都以新的身份连接
            options.setCleanSession(true);
            // 设置连接的用户名
            options.setUserName(userName);
            // 设置连接的密码
            options.setPassword(passWord.toCharArray());
            // 设置超时时间 单位为秒
            options.setConnectionTimeout(10);
            // 设置会话心跳时间 单位为秒 服务器会每隔1.5*20秒的时间向客户端发送个消息判断客户端是否在线，但这个方法并没有重连的机制
            options.setKeepAliveInterval(20);
            // 设置回调函数
            client.setCallback(mqttReceive);
            client.connect(options);
            System.out.println("连接成功！");
            //订阅消息
            client.subscribe(TOPICS,qos);
        } catch (Exception e) {
            try {
                assert client != null;
                client.unsubscribe(TOPICS);
            } catch (MqttException mqttException) {
                mqttException.printStackTrace();
            }
            try {
                client.disconnect();
            } catch (MqttException mqttException) {
                mqttException.printStackTrace();
            }
            e.printStackTrace();
        }
    }
}
