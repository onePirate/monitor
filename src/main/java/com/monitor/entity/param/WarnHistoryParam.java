package com.monitor.entity.param;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WarnHistoryParam {

    private String[] warnCodeArr;

    private String startTime;

    private String endTime;

}
