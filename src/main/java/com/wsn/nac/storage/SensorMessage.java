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
    private float leakageCurrent;
    private float smokeConcentration;
    private float tempData;
    private float humData;
    private float current;
    private float voltage;
    private float cumulateDegree;
    private Date createTime;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public float getLeakageCurrent() {
        return leakageCurrent;
    }

    public void setLeakageCurrent(float leakageCurrent) {
        this.leakageCurrent = leakageCurrent;
    }

    public float getSmokeConcentration() {
        return smokeConcentration;
    }

    public void setSmokeConcentration(float smokeConcentration) {
        this.smokeConcentration = smokeConcentration;
    }

    public float getTempData() {
        return tempData;
    }

    public void setTempData(float tempData) {
        this.tempData = tempData;
    }

    public float getHumData() {
        return humData;
    }

    public void setHumData(float humData) {
        this.humData = humData;
    }

    public float getCurrent() {
        return current;
    }

    public void setCurrent(float current) {
        this.current = current;
    }

    public float getVoltage() {
        return voltage;
    }

    public void setVoltage(float voltage) {
        this.voltage = voltage;
    }

    public float getCumulateDegree() {
        return cumulateDegree;
    }

    public void setCumulateDegree(float cumulateDegree) {
        this.cumulateDegree = cumulateDegree;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
