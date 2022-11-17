package com.wsn.nac.storage.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author: jia
 * Time: 2022/11/7  14:47
 * Description:
 * Version:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SensorData implements Serializable {
    private String id;//位置信息
    private String desc;//描述
    private int quality;
    private double value;//数据
}