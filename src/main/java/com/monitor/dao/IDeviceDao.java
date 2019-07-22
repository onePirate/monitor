package com.monitor.dao;

import com.monitor.entity.bo.ModelParamBo;
import com.monitor.entity.model.DeviceAgvModel;
import com.monitor.entity.model.DeviceCommonModel;
import com.monitor.entity.param.*;

import java.util.List;

public interface IDeviceDao {

    List<DeviceCommonModel> getInTimeData(ModelParamBo modelParamBo);

    List<DeviceAgvModel> getAgvInTimeData(ModelParamBo modelParamBo);

    int betchInsertAgv(List<AgvUploadParam> uploadParamList);

    int betchInsertYqj(List<YqjUploadParam> uploadParamList);

    int betchInsertRlj(List<RljUploadParam> uploadParamList);

    int betchInsertSsj(List<SsjUploadParam> uploadParamList);

    int betchInsertMdRobot(List<MonitorUploadParam> uploadParamList);

}
