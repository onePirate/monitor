package com.monitor.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.monitor.dao.IDeviceStatusDao;
import com.monitor.entity.mpModel.DeviceStatusModel;
import com.monitor.service.IDeviceStatusService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceStatusService extends ServiceImpl<IDeviceStatusDao, DeviceStatusModel> implements IDeviceStatusService {
    @Override
    public List<DeviceStatusModel> getInTimeData(int functionRoom) {
        return baseMapper.getInTimeData(functionRoom);
    }
}
