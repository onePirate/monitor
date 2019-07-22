package com.monitor.entity.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class WarnStatusModel {

    private int id;
    private int warnStatus;
    private String collectTime;
    private String warnIds;
    private String warnCodes;

}
