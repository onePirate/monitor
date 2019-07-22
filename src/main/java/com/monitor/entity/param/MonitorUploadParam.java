package com.monitor.entity.param;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MonitorUploadParam {

    protected int pointName;

    /**
     * 设备状态时专用字段
     */
    protected String deviceType;

    protected float pointValue;

    protected int switchStatus;

    protected String collectTime;

    protected String modifyUser;

    protected String modifyTime;

    /**
     * 工艺参数时专用字段
     */
    protected int pointType;

}
