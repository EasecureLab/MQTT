package com.wsn.nac.publish.entity;


public class sensor {

    private String name;
    private String deviceId;
    private String roomId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    @Override
    public String toString() {
        return "sensor{" +
                "name='" + name + '\'' +
                ", deviceId='" + deviceId + '\'' +
                ", roomId='" + roomId + '\'' +
                '}';
    }
}