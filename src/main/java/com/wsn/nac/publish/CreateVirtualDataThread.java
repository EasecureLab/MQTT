package com.wsn.nac.publish;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wsn.nac.publish.service.PushService;
import com.wsn.nac.publish.entity.*;
import com.wsn.nac.publish.service.sensorRead;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Component
public class CreateVirtualDataThread{

    @Autowired
    sensorRead sensorread;

    @Autowired
    PushService push;

    /**
     * 定时发送数据，每10分钟各类传感器发送一次
     */
    @SneakyThrows
    @Scheduled(fixedDelay = 1000*60*10)
    public void run(){

        //读取所有的烟雾传感器
        List<sensor> sensorsSmoke = sensorread.readOneSensor("smoke");
        for (sensor se : sensorsSmoke) {
            Random r = new Random();
            pushBody body = new pushBody();
            smoke sm = new smoke();
            DecimalFormat format = new DecimalFormat("#0.##");
            sm.setSmokeConcentration(Float.parseFloat(format.format(r.nextFloat() * 100)));
            sm.setDeviceId(se.getDeviceId());
            sm.setCreateTime(new Date());

            body.setTopics("smoke");
            body.setQos(2);
            body.setPayload(new ObjectMapper().writeValueAsString(sm));
            body.setRetain(false);
            body.setClientid("sendSensorData");
            System.out.println(sm.toString());

            push.pushToBroker(body);
        }

        Thread.sleep(5000);
        //读取所有的漏电流传感器
        List<sensor> sensorsLeakage = sensorread.readOneSensor("leakage");
        for(sensor se : sensorsLeakage){
            Random r = new Random();
            pushBody body = new pushBody();
            leakage le = new leakage();

            //漏电流大小一般为0.75mA
            DecimalFormat format = new DecimalFormat("#0.##");
            le.setLeakageCurrent(Float.parseFloat(format.format(r.nextFloat())));
            le.setDeviceId(se.getDeviceId());
            le.setCreateTime(new Date());

            body.setTopics("leakage");
            body.setQos(2);
            body.setPayload(new ObjectMapper().writeValueAsString(le));
            body.setRetain(false);
            body.setClientid("sendSensorData");
            System.out.println(le.toString());

            push.pushToBroker(body);
        }
        Thread.sleep(5000);
        List<sensor> sensorsTemperature = sensorread.readOneSensor("temperature");
        for(sensor se : sensorsTemperature) {
            Random r = new Random();
            pushBody body = new pushBody();
            temperature temp = new temperature();

            DecimalFormat format = new DecimalFormat("#0.##");
            temp.setTempData(Float.parseFloat(format.format(r.nextFloat()*30)));
            //湿度的单位是%
            temp.setHumData(Float.parseFloat(format.format(r.nextFloat()*100)));
            temp.setDeviceId(se.getDeviceId());
            temp.setCreateTime(new Date());

            body.setTopics("temperature");
            body.setQos(2);
            body.setPayload(new ObjectMapper().writeValueAsString(temp));
            body.setRetain(false);
            body.setClientid("sendSensorData");
            System.out.println(temp.toString());

            push.pushToBroker(body);
        }
        Thread.sleep(5000);
        List<sensor> sensorsElectricMeter = sensorread.readOneSensor("electricMeter");
        for(sensor se : sensorsElectricMeter) {
            Random r = new Random();
            pushBody body = new pushBody();
            electricMeter el = new electricMeter();
            el.setCurrent(r.nextFloat() * 10);
            el.setVoltage(220);
            el.setCumulateDegree(r.nextFloat() * 100);
            el.setDeviceId(se.getDeviceId());
            el.setCreateTime(new Date());

            body.setTopics("electricMeter");
            body.setQos(2);
            body.setPayload(new ObjectMapper().writeValueAsString(el));
            body.setRetain(false);
            body.setClientid("sendSensorData");
            System.out.println(el.toString());

            push.pushToBroker(body);
        }

    }
}
