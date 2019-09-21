package com.monitor.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.monitor.dao.IJqRobotDao;
import com.monitor.entity.mpModel.JqRobotModel;
import com.monitor.service.IJqRobotService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JqRobotService extends ServiceImpl<IJqRobotDao, JqRobotModel> implements IJqRobotService {
    @Override
    public List<JqRobotModel> getInTimeDatas() {
        return baseMapper.getDeviceInTimeDatas();
    }
}
