package com.wsn.nac.storage;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.wsn.nac.Util.TimeFormatSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SensorMessage implements Serializable {
    //对应数据库
    // @JsonIgnore
    // private final DeviceTypeEnum deviceType = DeviceTypeEnum.SENSOR;
    @Id
    private String id;
    //@Schema(description = "历史数据对应设备表的唯一id")
    //private String deviceId;   //先注释掉


    // 传感器历史应该返回一个Map,因为传感器的返回类型不定,使用固定字段不好扩展
    // private Float leakageCurrent;
    // private Float smokeConcentration;
    // private Float tempData;
    // private Float humData;
    // private Float current;
    // private Float voltage;
    // private Float cumulateDegree;
    private double data;
    // 便于mongo比较
    @JsonSerialize(using = TimeFormatSerializer.class)
    private Long dateTime;
    private Integer x;
    private Integer y;
}

//public class SensorMessage {
//    @Id
//    private String id;
//    @Schema(description = "将所有的传感器数据都放在这个类里面保存到数据库中", example = "{'smoke':54}")
//    String deviceId;
//    private Float leakageCurrent;
//    private Float smokeConcentration;
//    private Float tempData;
//    private Float humData;
//    private Float current;
//    private Float voltage;
//    private Float cumulateDegree;
//    private Date dateTime;
//    private String sensorType;
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public String getDeviceId() {
//        return deviceId;
//    }
//
//    public void setDeviceId(String deviceId) {
//        this.deviceId = deviceId;
//    }
//
//    public Float getLeakageCurrent() {
//        return leakageCurrent;
//    }
//
//    public void setLeakageCurrent(Float leakageCurrent) {
//        this.leakageCurrent = leakageCurrent;
//    }
//
//    public Float getSmokeConcentration() {
//        return smokeConcentration;
//    }
//
//    public void setSmokeConcentration(Float smokeConcentration) {
//        this.smokeConcentration = smokeConcentration;
//    }
//
//    public Float getTempData() {
//        return tempData;
//    }
//
//    public void setTempData(Float tempData) {
//        this.tempData = tempData;
//    }
//
//    public Float getHumData() {
//        return humData;
//    }
//
//    public void setHumData(Float humData) {
//        this.humData = humData;
//    }
//
//    public Float getCurrent() {
//        return current;
//    }
//
//    public void setCurrent(Float current) {
//        this.current = current;
//    }
//
//    public Float getVoltage() {
//        return voltage;
//    }
//
//    public void setVoltage(Float voltage) {
//        this.voltage = voltage;
//    }
//
//    public Float getCumulateDegree() {
//        return cumulateDegree;
//    }
//
//    public void setCumulateDegree(Float cumulateDegree) {
//        this.cumulateDegree = cumulateDegree;
//    }
//
//    public Date getDateTime() {
//        return dateTime;
//    }
//
//    public void setDateTime(Date dateTime) {
//        this.dateTime = dateTime;
//    }
//
//    public String getSensorType() {
//        return sensorType;
//    }
//
//    public void setSensorType(String sensorType) {
//        this.sensorType = sensorType;
//    }
//
//    @Override
//    public String toString() {
//        return "SensorMessage{" +
//                "deviceId='" + deviceId + '\'' +
//                ", leakageCurrent=" + leakageCurrent +
//                ", smokeConcentration=" + smokeConcentration +
//                ", tempData=" + tempData +
//                ", humData=" + humData +
//                ", current=" + current +
//                ", voltage=" + voltage +
//                ", cumulateDegree=" + cumulateDegree +
//                ", dateTime=" + dateTime +
//                '}';
//    }
//}
