package com.monitor.dao;

import com.monitor.entity.bo.CleanParamBo;
import com.monitor.entity.bo.InsertModelParamBo;
import com.monitor.entity.bo.ModelParamBo;
import com.monitor.entity.model.CommonMonitorModel;

import java.util.List;

public interface ICommonMonitorDao {

    List<CommonMonitorModel> getInTimeData(ModelParamBo modelParamBo);

    List<CommonMonitorModel> getMonitorHistory(ModelParamBo modelParamBo);

    int batchInsert(InsertModelParamBo insertModelParamBo);

    int cleanMonitorData(CleanParamBo cleanParamBo);

}
