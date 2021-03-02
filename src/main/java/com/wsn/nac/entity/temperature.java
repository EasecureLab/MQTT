package com.wsn.nac.entity;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Date;

public class temperature {

    @Schema(description = "设备Id, 第一个字符是“t”代表它是温湿度传感器，剩下的字符表示它的设备编号", example = "t01")
    private String deviceId;
    private float tempData;
    private float humData;
    private Date createTime;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "temperature{" +
                "deviceId='" + deviceId + '\'' +
                ", tempData='" + tempData + '\'' +
                ", humData='" + humData + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
