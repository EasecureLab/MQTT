package com.wsn.nac.publish.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.annotation.Id;

import java.util.Date;

public class temperature {

    @Id
    private String id;

    @Schema(description = "设备Id, 第一个字符是“t”代表它是温湿度传感器，剩下的字符表示它的设备编号", example = "t01")
    private String deviceId;
    private float tempData;
    private float humData;
    private Date dateTime;
    private String sensorType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public String getSensorType() {
        return sensorType;
    }

    public void setSensorType(String sensorType) {
        this.sensorType = sensorType;
    }

    @Override
    public String toString() {
        return "temperature{" +
                "deviceId='" + deviceId + '\'' +
                ", tempData=" + tempData +
                ", humData=" + humData +
                ", dateTime=" + dateTime +
                '}';
    }
}
