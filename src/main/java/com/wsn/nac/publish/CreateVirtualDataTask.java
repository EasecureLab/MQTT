package com.wsn.nac.publish;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wsn.nac.publish.service.PushService;
import com.wsn.nac.publish.entity.*;
import com.wsn.nac.publish.service.sensorRead;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.TimerTask;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CreateVirtualDataTask extends TimerTask {

    final private sensorRead sensorread;
    final private PushService push;



    /**
     * 定时发送数据，每半分钟各类传感器发送一次
     */
    //@Scheduled(fixedDelay = 1000*30)
    @SneakyThrows
    @Override
    public void run(){

        //读取所有的烟雾传感器
        System.out.println("开始生成数据");
        List<sensor> sensorsSmoke = sensorread.readOneSensor("smoke");
        if(sensorsSmoke != null){
            for (sensor se : sensorsSmoke) {
                Random r = new Random();
                pushBody body = new pushBody();
                smoke sm = new smoke();
                DecimalFormat format = new DecimalFormat("#0.##");
                sm.setSmokeConcentration(Float.parseFloat(format.format(r.nextFloat() * 100)));
                sm.setDeviceId(se.getDeviceId());
                sm.setDateTime(new Date());
                sm.setId(se.getId());

                body.setTopics("smoke");
                body.setQos(2);
                body.setPayload(new ObjectMapper().writeValueAsString(sm));
                body.setRetain(false);
                body.setClientid("sendSensorData");
                System.out.println(sm.toString());
                Thread.sleep(500);
                push.pushToBroker(body);
            }
        }


        //读取所有的漏电流传感器
        List<sensor> sensorsLeakage = sensorread.readOneSensor("leakage");
        if(sensorsLeakage != null){
            for(sensor se : sensorsLeakage){
                Random r = new Random();
                pushBody body = new pushBody();
                leakage le = new leakage();

                //漏电流大小一般为0.75mA
                DecimalFormat format = new DecimalFormat("#0.##");
                le.setLeakageCurrent(Float.parseFloat(format.format(r.nextFloat())));
                le.setDeviceId(se.getDeviceId());
                le.setDateTime(new Date());
                le.setId(se.getId());

                body.setTopics("leakage");
                body.setQos(2);
                body.setPayload(new ObjectMapper().writeValueAsString(le));
                body.setRetain(false);
                body.setClientid("sendSensorData");
//            System.out.println(le.toString());
                Thread.sleep(500);
                push.pushToBroker(body);
            }
        }


        List<sensor> sensorsTemperature = sensorread.readOneSensor("temperature");
        if(sensorsTemperature != null){
            for(sensor se : sensorsTemperature) {
                Random r = new Random();
                pushBody body = new pushBody();
                temperature temp = new temperature();

                DecimalFormat format = new DecimalFormat("#0.##");
                temp.setTempData(Float.parseFloat(format.format(r.nextFloat()*30)));
                //湿度的单位是%
                temp.setHumData(Float.parseFloat(format.format(r.nextFloat()*100)));
                temp.setDeviceId(se.getDeviceId());
                temp.setDateTime(new Date());
                temp.setId(se.getId());

                body.setTopics("temperature");
                body.setQos(2);
                body.setPayload(new ObjectMapper().writeValueAsString(temp));
                body.setRetain(false);
                body.setClientid("sendSensorData");
//            System.out.println(temp.toString());
                Thread.sleep(500);
                push.pushToBroker(body);
            }
        }


        List<sensor> sensorsElectricMeter = sensorread.readOneSensor("electricMeter");
        if(sensorsElectricMeter != null){
            for(sensor se : sensorsElectricMeter) {
                Random r = new Random();
                pushBody body = new pushBody();
                electricMeter el = new electricMeter();
                DecimalFormat format = new DecimalFormat("#0.##");
                el.setCurrent(Float.parseFloat(format.format(r.nextFloat() * 10)));
                el.setVoltage(220);
                el.setCumulateDegree(Float.parseFloat(format.format(r.nextFloat() * 100)));
                el.setDeviceId(se.getDeviceId());
                el.setDateTime(new Date());
                el.setId(se.getId());

                body.setTopics("electricMeter");
                body.setQos(2);
                body.setPayload(new ObjectMapper().writeValueAsString(el));
                body.setRetain(false);
                body.setClientid("sendSensorData");
//            System.out.println(el.toString());
                Thread.sleep(500);
                push.pushToBroker(body);
            }
        }
    }
}
