package com.wsn.nac.storage;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Date;

/**
 * UTF-8
 * Created by czy  Time : 2021/3/3 11:02
 *
 * @version 1.0
 */
public class SensorMessage {
    @Schema(description = "将所有的传感器数据都放在这个类里面保存到数据库中", example = "{'smoke':54}")
    String deviceId;
    private Float leakageCurrent;
    private Float smokeConcentration;
    private Float tempData;
    private Float humData;
    private Float current;
    private Float voltage;
    private Float cumulateDegree;
    private Date createTime;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public Float getLeakageCurrent() {
        return leakageCurrent;
    }

    public void setLeakageCurrent(Float leakageCurrent) {
        this.leakageCurrent = leakageCurrent;
    }

    public Float getSmokeConcentration() {
        return smokeConcentration;
    }

    public void setSmokeConcentration(Float smokeConcentration) {
        this.smokeConcentration = smokeConcentration;
    }

    public Float getTempData() {
        return tempData;
    }

    public void setTempData(Float tempData) {
        this.tempData = tempData;
    }

    public Float getHumData() {
        return humData;
    }

    public void setHumData(Float humData) {
        this.humData = humData;
    }

    public Float getCurrent() {
        return current;
    }

    public void setCurrent(Float current) {
        this.current = current;
    }

    public Float getVoltage() {
        return voltage;
    }

    public void setVoltage(Float voltage) {
        this.voltage = voltage;
    }

    public Float getCumulateDegree() {
        return cumulateDegree;
    }

    public void setCumulateDegree(Float cumulateDegree) {
        this.cumulateDegree = cumulateDegree;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
