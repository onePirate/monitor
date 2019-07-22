package com.monitor.entity.bo;

import com.monitor.entity.param.MonitorUploadParam;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class InsertModelParamBo {

    private String tableName;

    private List<MonitorUploadParam> monitorUploadParamList;

}
