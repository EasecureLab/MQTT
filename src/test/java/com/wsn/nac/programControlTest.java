package com.wsn.nac;

import com.wsn.nac.publish.CreateVirtualDataTask;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Timer;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class programControlTest {


    Timer timer = new Timer();

    @Autowired
    CreateVirtualDataTask task;

    //设置定时时间并启动定时器
    @Test
    public void setParamTest(){
        System.out.println("开始程序，以"+1+"分钟的速率重复产生数据！");
        timer.scheduleAtFixedRate(task,0,1000*60);
    }
}
