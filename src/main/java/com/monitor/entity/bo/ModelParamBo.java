package com.monitor.entity.bo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class ModelParamBo {

    private String tableName;

    private String startTime;

    private String endTime;

    private String[] pNameArr;

    public ModelParamBo(String tableName, String[] pNameArr) {
        this.tableName = tableName;
        this.pNameArr = pNameArr;
    }
}


