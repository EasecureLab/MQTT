package com.wsn.nac.publish.controller;

import com.wsn.nac.publish.service.UseQuartZControlService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "传感器控制")
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StartProgramController {


     final private UseQuartZControlService usequartZ;
     final private Scheduler scheduler;

    @ApiOperation(value = "controlStart")
    @GetMapping("/start")
    public void startProgram() throws SchedulerException {
        usequartZ.createScheduleJob(scheduler);
    }


    @ApiOperation(value = "PostFrequencyParam")
    @PostMapping("/frequency")
    public void changeFrequency(
            @RequestParam(name = "frequencyParam") int frequencyParam) throws SchedulerException {
        usequartZ.setParam(scheduler,frequencyParam);
    }


    @ApiOperation(value = "controlStop")
    @GetMapping("/end")
    public void endProgram(){
        usequartZ.deleteScheduleJob(scheduler);
    }

    @ApiOperation(value = "controlPause")
    @GetMapping("/pause")
    public void pauseProgram(){
        usequartZ.pauseScheduleJob(scheduler);
    }

    @ApiOperation(value = "controlResume")
    @GetMapping("/resume")
    public void resumeProgram(){
        usequartZ.resumeScheduleJob(scheduler);
    }

    @GetMapping("/check")
    @Operation(summary = "这是一个测试连接, 如果有输出,则说明服务正在运行")
    public String test() {
        return "这是一个测试连接, 如果有输出,则说明服务正在运行";
    }

}
