package com.wsn.nac.publish.service;

import com.wsn.nac.publish.CreateVirtualDataTask;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Timer;
/**
//@Service
//@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProgramControlService {

    Timer timer = new Timer();

    final private CreateVirtualDataTask task;

    //设置默认情况下开启定时器，默认每1分钟产生数据
    public void startProgram(){

        timer.scheduleAtFixedRate(task,0,1000*60);



    }

    //设置定时时间并启动定时器
    public void setParam(long param){
        System.out.println("开始程序，以"+param+"分钟的速率重复产生数据！");
        timer.scheduleAtFixedRate(task,0,param*1000*60);
    }

    //结束定时器
    public void stopProgram(){

        timer.cancel();
        System.out.println("结束程序，停止产生数据！");

    }



}
*/