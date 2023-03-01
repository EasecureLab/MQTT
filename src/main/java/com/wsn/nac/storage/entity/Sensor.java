package com.wsn.nac.storage.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.wsn.nac.Util.TimeFormatSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Sensor implements Serializable {

    private static final long serialVersionUID = -8425727796598355951L;

    @MongoId
    private String _id;

    private String deviceId;//位置信息
    private String desc;//描述
    private int quality;
    private double data;//数据
    // 便于mongo比较
    @JsonSerialize(using = TimeFormatSerializer.class)
    private Long dateTime;


}
