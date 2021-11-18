package com.wsn.nac;

import com.wsn.nac.storage.MessageStore;
import com.wsn.nac.storage.SensorMessage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class saveTest {

    @Autowired
    MessageStore messageStore;

    @Test
    public void storeTest(){
        SensorMessage message = new SensorMessage();
        message.setId("");
        message.setDeviceId("e01");
        message.setCurrent((float) 4.33);
        message.setVoltage(220.0F);
        message.setDateTime(new Date());

        messageStore.storeByCollectionId(message,"e01");
    }
}
