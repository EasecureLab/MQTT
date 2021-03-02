package com.wsn.nac.entity;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Date;

public class smoke {

    @Schema(description = "设备Id, 第一个字符是“m”代表它是烟雾监测传感器，剩下的字符表示它的设备编号", example = "m01")
    private String deviceId;
    private float smokeConcentration;
    private Date createTime;

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "smoke{" +
                "deviceId='" + deviceId + '\'' +
                ", smokeConcentration=" + smokeConcentration +
                ", createTime=" + createTime +
                '}';
    }
}
