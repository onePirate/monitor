package com.monitor.service;

import com.monitor.entity.model.CommonMonitorModel;
import com.monitor.entity.param.EnvQueryHistoryParam;
import com.monitor.entity.param.EnvQueryInTimeParam;
import com.monitor.entity.param.MonitorUploadParam;

import java.util.List;

public interface IMonitorService {

    List<CommonMonitorModel> getEnvMonitorInTimeQuery(EnvQueryInTimeParam envQueryInTimeParam);


    List<CommonMonitorModel> getEnvMonitorHistoryQuery(EnvQueryHistoryParam envQueryHistoryParam);

    int batchInsert(List<MonitorUploadParam> monitorUploadParamList);

}
