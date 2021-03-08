package com.wsn.nac.publish.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.annotation.Id;

import java.util.Date;

public class electricMeter {

    @Id
    private String id;
    @Schema(description = "设备Id, 第一个字符是“e”代表它是智能电表，剩下的字符表示它的设备编号", example = "e01")
    private String deviceId;
    private float current;
    private float voltage;
    private float cumulateDegree;
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

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "electricMeter{" +
                "deviceId='" + deviceId + '\'' +
                ", current=" + current +
                ", voltage=" + voltage +
                ", cumulateDegree=" + cumulateDegree +
                ", dateTime=" + dateTime +
                '}';
    }
}
