package com.monitor.service;

import com.monitor.entity.model.WarnExtendModel;
import com.monitor.entity.param.WarnHistoryParam;
import com.monitor.entity.param.WarnUploadParam;

import java.util.List;

public interface IWarnService {

    List<WarnExtendModel> getInTimeWarns();

    int insertWarns(List<WarnUploadParam> warnUploadParams);

    List<WarnExtendModel> getHistoryWarns(WarnHistoryParam warnHistoryParam);

}
