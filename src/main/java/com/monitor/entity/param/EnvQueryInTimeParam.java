package com.monitor.entity.param;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EnvQueryInTimeParam {

    /**
     * 点位信息数组
     */
    private String[] pointArr;

    /**
     * 查询点位类型
     */
    private int pointType;

}
