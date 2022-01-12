package com.wsn.nac.storage;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageFormat implements Serializable {
    private String type;
    private String sn;
    private String time;
    private SensorChannel data;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SensorChannel implements Serializable {
        // 注解解决将json格式字符串大写自动转换为小写的问题
        @JsonProperty("C1_D1")
        private List<SensorData> C1_D1;
        @JsonProperty("C1_D2")
        private List<SensorData> C1_D2;
        @JsonProperty("C1_D3")
        private List<SensorData> C1_D3;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SensorData implements Serializable {
        private String id;
        private String desc;
        private int quality;
        private double value;
    }
}
