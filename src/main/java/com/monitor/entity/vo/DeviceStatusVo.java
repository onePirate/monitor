package com.monitor.entity.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeviceStatusVo  extends CommonPropertyVo {

    /**
     * 点位类型
     */
    private String deviceType;

    private String pointValue;
}
