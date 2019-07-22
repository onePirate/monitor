package com.monitor.entity.param;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RljUploadParam extends MonitorUploadParam {

    private float waterLine;
    private float temperature;
    private int rotateSpeed;

}
