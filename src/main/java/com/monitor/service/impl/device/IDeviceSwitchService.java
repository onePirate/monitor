package com.monitor.service.impl.device;

import com.monitor.dao.IDeviceStatusDao;
import com.monitor.entity.bo.InsertModelParamBo;
import com.monitor.entity.bo.ModelParamBo;
import com.monitor.entity.param.MonitorUploadParam;
import com.monitor.service.IDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IDeviceSwitchService implements IDeviceService {

    @Autowired
    IDeviceStatusDao deviceStatusDao;

    @Override
    public Object getDeviceInTimeStatus(String tableName) {
        return deviceStatusDao.getInTimeData(new ModelParamBo(tableName,null));
    }

    @Override
    public int batchInsert(String tableName,Object uploadParamList) {
        return deviceStatusDao.batchInsert(new InsertModelParamBo(tableName,(List<MonitorUploadParam>)uploadParamList));
    }
}
