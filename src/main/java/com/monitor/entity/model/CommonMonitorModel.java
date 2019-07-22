package com.monitor.entity.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommonMonitorModel {

    private int id;

    private int pointName;

    private float pointValue;

    private String collectTime;

    private String modifyUser;

    private String modifyTime;

}
