package com.monitor.entity.param;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WarnUploadParam {

    private int id;
    private String warnCode;
    private String deviceType;
    private String warnInfo;
    private String warnCondition;
    private String remark;
    private String autoBack;
    private String collectTime;
    private String modifyUser;
    private String modifyTime;

}
