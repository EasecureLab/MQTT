package com.wsn.nac.publish.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.annotation.Id;

import java.util.Date;

public class leakage {

    @Id
    private String id;

    @Schema(description = "设备Id, 第一个字符是“l”代表它是漏电传感器，剩下的字符表示它的设备编号", example = "l01")
    private String deviceId;
    private float leakageCurrent;
    private Date dateTime;

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

    public float getLeakageCurrent() {
        return leakageCurrent;
    }

    public void setLeakageCurrent(float leakageCurrent) {
        this.leakageCurrent = leakageCurrent;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "leakage{" +
                "deviceId='" + deviceId + '\'' +
                ", leakageCurrent=" + leakageCurrent +
                ", dateTime=" + dateTime +
                '}';
    }
}
