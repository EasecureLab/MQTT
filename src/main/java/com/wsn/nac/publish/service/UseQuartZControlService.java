package com.wsn.nac.publish.service;

import com.wsn.nac.publish.CreateVirtualDataTask;
import lombok.RequiredArgsConstructor;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UseQuartZControlService {

    final private CreateVirtualDataTask jobBean;

    /**
     * 创建并开始运行一个定时任务,默认10秒钟产生一次数据
     * @param scheduler  调度器
     */
    public  void createScheduleJob(Scheduler scheduler) throws SchedulerException {
        // 构建定时任务信息
        JobDetail jobDetail = JobBuilder.newJob(CreateVirtualDataTask.class).withIdentity("sensorData").build();

        // 构建触发器trigger,设置定时一分钟重复执行
        SimpleTrigger trigger = TriggerBuilder.newTrigger().startNow().withIdentity("sensorData")
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(10).repeatForever()).build();

        //调度器，开始运行任务
        scheduler.scheduleJob(jobDetail,trigger);
        scheduler.start();
    }

    /**
     * 暂停定时任务
     * @param scheduler  调度器
     */
    public void pauseScheduleJob(Scheduler scheduler){
        JobKey jobKey = JobKey.jobKey("sensorData");
        try {

            scheduler.pauseJob(jobKey);
            System.out.println("暂停数据的产生");
        } catch (SchedulerException e) {
            System.out.println("暂停定时任务出错："+e.getMessage());
        }
    }

    /**
     * 恢复定时任务
     * @param scheduler  调度器
     */
    public void resumeScheduleJob(Scheduler scheduler) {
        JobKey jobKey = JobKey.jobKey("sensorData");
        try {
            scheduler.resumeJob(jobKey);
            System.out.println("恢复数据的产生");
        } catch (SchedulerException e) {
            System.out.println("恢复定时任务出错："+e.getMessage());
        }
    }

    /**
     * 根据传入的参数设定数据产生的周期
     * @param scheduler   调度器
     * @param param 频率
     */
    public void setParam(Scheduler scheduler,int param) throws SchedulerException {

        JobDetail jobDetail = JobBuilder.newJob(CreateVirtualDataTask.class).withIdentity("test").build();
        SimpleTrigger trigger = TriggerBuilder.newTrigger().startNow().withIdentity("test")
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInMinutes(param).repeatForever()).build();
        scheduler.scheduleJob(jobDetail,trigger);
        scheduler.start();
    }

    /**
     * 删除定时任务
     * @param scheduler 调度器
     */
    public void deleteScheduleJob(Scheduler scheduler) {
        JobKey jobKey = JobKey.jobKey("sensorData");
        try {
            scheduler.deleteJob(jobKey);
            System.out.println("结束数据的产生");
        } catch (SchedulerException e) {
            System.out.println("删除定时任务出错："+e.getMessage());
        }
    }
}
