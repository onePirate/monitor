package com.monitor.dao;

import com.monitor.entity.bo.InsertModelParamBo;
import com.monitor.entity.bo.ModelParamBo;
import com.monitor.entity.model.DeviceStatusModel;

import java.util.List;

public interface IDeviceStatusDao {

    List<DeviceStatusModel> getInTimeData(ModelParamBo modelParamBo);

    int batchInsert(InsertModelParamBo insertModelParamBo);

}
