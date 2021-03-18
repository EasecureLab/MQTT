package com.wsn.nac;

import com.wsn.nac.publish.entity.sensor;
import com.wsn.nac.publish.service.sensorRead;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class readSensorTest {

    @Autowired
    sensorRead sensorread;


    @Test
    public void readTest(){
        List<sensor> sensors = sensorread.readOneSensor("smoke");
        System.out.println(sensors);
    }



}
