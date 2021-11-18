package com.wsn.nac;


import com.wsn.nac.publish.service.UseQuartZControlService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.Scheduler;
import org.quartz.impl.StdScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class programControlTest {

    @Autowired
    UseQuartZControlService useQuartZControlService;

    @Test
    public void startTest(){

    }
}
