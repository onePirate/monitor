package com.monitor.entity.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DeviceStatusModel {

    private int id;

    private int pointName;

    private String pointValue;

    private String deviceType;

    private String collectTime;

    private String modifyUser;

    private String modifyTime;

}
