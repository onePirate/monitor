package com.monitor.entity.param;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EnvQueryHistoryParam {

    private String startTime;

    private String endTime;

    private int pageIndex;

    private int pageSize;
    /**
     * 点位信息数组
     */
    private String[] pointArr;

    /**
     * 查询点位类型
     */
    private int pointType;

}
