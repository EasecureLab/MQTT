package com.wsn.nac.storage.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageFormat implements Serializable {
    private static final long serialVersionUID = 62912491682529941L;
    // type: real 全部数据
    // type: change 变化数据
    // type: desc 设备描述信息,格式为type,sn,info
    private String type; // 一般为real
    private String sn; //采集器标识
    private String time;
    private JsonNode data;

    // 某些message传过来会携带info字段，携带info字段的type为desc,该message为描述信息，不进行处理
    // 包含info的  ->  type一定为desc
    private JsonNode info;

    // @Data
    // @AllArgsConstructor
    // @NoArgsConstructor
    // public static class SensorChannel implements Serializable {
    //
    //     Map<String, List<MessageFormat.SensorData>> readValue;
    //     // // 注解解决将json格式字符串大写自动转换为小写的问题
    //     // @JsonProperty("C1_D1")
    //     // private List<SensorData> C1_D1;
    //     // @JsonProperty("C1_D2")
    //     // private List<SensorData> C1_D2;
    //     // @JsonProperty("C1_D3")
    //     // private List<SensorData> C1_D3;
    //     // @JsonProperty("C2_D1")
    //     // private List<SensorData> C2_D1;
    //     // @JsonProperty("C3_D1")
    //     // private List<SensorData> C3_D1;
    //
    // }


}
