package com.wsn.nac.storage.mqtt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wsn.nac.Util.TimeFormatTransUtils;
import com.wsn.nac.storage.entity.MessageFormat;
import com.wsn.nac.storage.MessageStore;
import com.wsn.nac.storage.entity.Sensor;
import com.wsn.nac.storage.entity.SensorData;
import com.wsn.nac.storage.common.ScreenEnum;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * UTF-8
 * Created by czy  Time : 2021/3/3 9:58
 *
 * @version 1.0
 */
@Component
@Slf4j
public class MqttReceiveCallback implements MqttCallback {

    // 服务器cpu个数为8，核数为1
    ExecutorService threadPool = new ThreadPoolExecutor(4,
            9,2L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1000));

    @Autowired
    MessageStore messageStore;

    @Override
    public void connectionLost(Throwable throwable) {
        // 连接丢失后，在这里面进行重连，如接收消息类中设置 options.setAutomaticReconnect(true);则此处可不做设置，会自动重连
        log.error(throwable.getMessage());
        log.error("连接丢失");
    }

    @Override
    public void messageArrived(String topic, MqttMessage mqttMessage) throws JsonProcessingException {
        String message = new String(mqttMessage.getPayload(), StandardCharsets.UTF_8);

        log.info("订阅的消息内容：");
        log.info("messageArrived() topic: "+topic+", message is "+message);

        MessageFormat messageFormat = new ObjectMapper().readValue(message, MessageFormat.class);
        // 存在info字段，不处理，直接返回
        if (messageFormat.getInfo() != null){
            log.warn("包含info，为描述信息");
            return;
        }
        // data为空，直接返回
        if (messageFormat.getData() == null || messageFormat.getData().toString().length() == 0) {
            log.warn("data为空");
            return;
        }
        // // 变化的数据不做处理，直接返回
        // if ("change".equals(messageFormat.getType())){
        //     log.warn("变化数据，不做处理");
        //     return;
        // }

        // System.out.println("data:" + messageFormat.getData());

        // 将data按map进行解析
        // key 为 C1D1,C1D2,C2D1,C3D1...
        // value 为 List<SensorData>,  这里直接解析 List<SensorData> 会报错，先存为Object, 后面再转化为SensorData
        Map<String,List<Object>> readValue = new ObjectMapper().readValue(messageFormat.getData().toString(), Map.class);
        // 遍历 map
        for (List<Object> listObject : readValue.values()) {
            // 遍历 List<Object>
            for (Object object : listObject) {
                // Object 转化为 SensorData：Object转化为json，再从json中解析出SensorData
                String jsonObject = new ObjectMapper().writeValueAsString(object);
                SensorData sensorData = new ObjectMapper().readValue(jsonObject, SensorData.class);
                log.info("sensorData: " + sensorData);
                // id 为 _io_status 的不做操作
                if ("_io_status".equals(sensorData.getId())) {
                    continue;
                }
                // 处理SensorData
                threadPool.execute(new Runnable() {
                    @Override
                    public void run() {
                        handleSensorData(sensorData);
                    }
                });

            }
        }
    }

    /**
     * @param sensorData:
     * @return void
     * @author jia
     * @description 处理收到的sensorData，将其存入数据库中
     * @date 2022/8/29 17:05
     */
    public void handleSensorData(SensorData sensorData){
        if ("screen-temperature".equals(sensorData.getDesc())){  //大屏温度
            // null开头的不做处理
            if (sensorData.getId().startsWith("null")){
                return;
            }

            String[] positionData = sensorData.getId().split("-");

            // System.out.println(Arrays.toString(positionData));

            int position = Integer.parseInt(positionData[0]);
            ScreenEnum screen = ScreenEnum.select(position);
            // int X = Integer.parseInt(positionData[1]);
            // int Y = Integer.parseInt(positionData[2]);
            // SensorMessage sensorMessage = new SensorMessage();
            // sensorMessage.setX(X);
            // sensorMessage.setY(Y);
            // sensorMessage.setDateTime(TimeFormatTransUtils.localDateTime2timeStamp(LocalDateTime.now()));
            // sensorMessage.setData(sensorData.getValue());
            //
            // log.info("sensorMessageDataBase:" + sensorMessage);
            // messageStore.storeByCollectionName(sensorMessage, screen.toString() + "Test4");
            Sensor sensor = new Sensor();
            sensor.setDeviceId(sensorData.getId());
            sensor.setDesc(sensorData.getDesc());
            sensor.setData(sensorData.getValue());
            sensor.setDateTime(TimeFormatTransUtils.localDateTime2timeStamp(LocalDateTime.now()));
            // 1-6-23 位置的数据有问题，直接设置为25
            if (sensor.getDeviceId().equals("1-6-23")){
                sensor.setData(25);
            }
            messageStore.storeByCollectionName(sensor, screen.toString());

        }else { // 其他传感器
            // log.info("sensorData:" + sensorData);
            Sensor sensor = new Sensor();
            sensor.setDeviceId(sensorData.getId());
            sensor.setDesc(sensorData.getDesc());
            sensor.setData(sensorData.getValue());
            sensor.setDateTime(TimeFormatTransUtils.localDateTime2timeStamp(LocalDateTime.now()));

            log.info("sensorDataBase:" + sensor);
            messageStore.storeByCollectionName(sensor, ScreenEnum.OTHERS.toString());
        }
        log.info("保存成功");

    }


    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

    }
}
