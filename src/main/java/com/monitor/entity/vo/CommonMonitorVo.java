package com.monitor.entity.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommonMonitorVo extends CommonPropertyVo {

    /**
     * 点位类型
     */
    private int pNameType;

    private float pointValue;


}
