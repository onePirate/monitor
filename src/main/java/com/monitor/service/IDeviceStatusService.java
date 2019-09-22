package com.monitor.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.monitor.entity.mpModel.DeviceStatusModel;

import java.util.List;

public interface IDeviceStatusService extends IService<DeviceStatusModel> {

    List<DeviceStatusModel> getInTimeData(List<Integer> functionRoom);

}
