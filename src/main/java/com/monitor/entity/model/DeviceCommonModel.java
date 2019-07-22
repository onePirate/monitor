package com.monitor.entity.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeviceCommonModel {

    protected int id;
    protected int pointName;
    protected int switchStatus;
    protected String collectTime;
    protected String dataType;
    protected String modifyUser;
    protected String modifyTime;

}
