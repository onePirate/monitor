package com.monitor.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.monitor.entity.mpModel.DeviceStatusModel;

import java.util.List;

public interface IDeviceStatusDao extends BaseMapper<DeviceStatusModel> {

    List<DeviceStatusModel> getInTimeData(List<Integer> functionRoom);

}
