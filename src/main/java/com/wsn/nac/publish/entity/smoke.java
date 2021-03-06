package com.wsn.nac.publish.entity;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Date;

public class smoke {

    @Schema(description = "设备Id, 第一个字符是“s”代表它是烟雾监测传感器，剩下的字符表示它的设备编号", example = "s01")
    private String deviceId;
    private float smokeConcentration;
    private Date dateTime;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public float getSmokeConcentration() {
        return smokeConcentration;
    }

    public void setSmokeConcentration(float smokeConcentration) {
        this.smokeConcentration = smokeConcentration;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "smoke{" +
                "deviceId='" + deviceId + '\'' +
                ", smokeConcentration=" + smokeConcentration +
                ", dateTime=" + dateTime +
                '}';
    }
}
