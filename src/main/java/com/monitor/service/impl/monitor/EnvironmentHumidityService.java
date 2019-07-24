package com.monitor.service.impl.monitor;


import com.monitor.common.constant.CommonConstant;
import com.monitor.dao.ICommonMonitorDao;
import com.monitor.entity.bo.CleanParamBo;
import com.monitor.entity.bo.InsertModelParamBo;
import com.monitor.entity.bo.ModelParamBo;
import com.monitor.entity.model.CommonMonitorModel;
import com.monitor.entity.param.EnvQueryHistoryParam;
import com.monitor.entity.param.EnvQueryInTimeParam;
import com.monitor.entity.param.MonitorUploadParam;
import com.monitor.service.IMonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnvironmentHumidityService implements IMonitorService {
    @Autowired
    ICommonMonitorDao commonMonitorDao;

    @Override
    public List<CommonMonitorModel> getEnvMonitorInTimeQuery(EnvQueryInTimeParam envQueryInTimeParam) {
        return commonMonitorDao.getInTimeData(new ModelParamBo(CommonConstant.TABLE_ENV_HUMIDITY, envQueryInTimeParam.getPointArr()));
    }

    @Override
    public List<CommonMonitorModel> getEnvMonitorHistoryQuery(EnvQueryHistoryParam envQueryHistoryParam) {
        return commonMonitorDao.getMonitorHistory(new ModelParamBo(CommonConstant.TABLE_ENV_HUMIDITY,
                envQueryHistoryParam.getStartTime(),envQueryHistoryParam.getEndTime(),envQueryHistoryParam.getPointArr()));
    }

    @Override
    public int batchInsert(List<MonitorUploadParam> monitorUploadParamList) {
        return commonMonitorDao.batchInsert(new InsertModelParamBo(CommonConstant.TABLE_ENV_HUMIDITY,monitorUploadParamList));
    }

    @Override
    public void cleanEnvData(String cleanTime) {
        commonMonitorDao.cleanMonitorData(new CleanParamBo(cleanTime,CommonConstant.TABLE_ENV_HUMIDITY));
    }
}