package com.wsn.nac.publish.controller;

import com.wsn.nac.publish.service.ProgramControlService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "传感器控制")
@RestController
public class StartProgramController {

    @Autowired
    ProgramControlService programControlService;

    @ApiOperation(value = "controlStart")
    @GetMapping("/start")
    public void startProgram(){
        programControlService.startProgram();
    }


    @ApiOperation(value = "PostFrequencyParam")
    @PostMapping("/frequency")
    public void changeFrequency(
            @RequestParam(name = "frequencyParam") long frequencyParam){
        programControlService.setParam(frequencyParam);
    }


    @ApiOperation(value = "controlStop")
    @GetMapping("/end")
    public void endProgram(){
        programControlService.stopProgram();
    }
}
