package com.wsn.nac.entity;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Date;

public class electricMeter {

    @Schema(description = "设备Id, 第一个字符是“e”代表它是智能电表，剩下的字符表示它的设备编号", example = "e01")
    private String deviceId;
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

    @Override
    public String toString() {
        return "smartMeter{" +
                "deviceId='" + deviceId + '\'' +
                ", current=" + current +
                ", voltage=" + voltage +
                ", cumulateDegree=" + cumulateDegree +
                ", createTime=" + createTime +
                '}';
    }
}
