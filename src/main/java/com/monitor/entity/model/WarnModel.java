package com.monitor.entity.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WarnModel {

    protected int id;
    protected String warnCode;
    protected String deviceType;
    protected String warnInfo;
    protected String warnCondition;
    protected String remark;
    protected String autoBack;
    protected String collectTime;
    protected String continueToTime;
    protected String modifyUser;
    protected String modifyTime;
}
