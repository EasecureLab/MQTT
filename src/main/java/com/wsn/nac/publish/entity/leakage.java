package com.wsn.nac.publish.entity;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Date;

public class leakage {

    @Schema(description = "设备Id, 第一个字符是“l”代表它是漏电传感器，剩下的字符表示它的设备编号", example = "l01")
    private String deviceId;
    private float leakageCurrent;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "leakage{" +
                "deviceId='" + deviceId + '\'' +
                ", leakageCurrent=" + leakageCurrent +
                ", createTime=" + createTime +
                '}';
    }
}
