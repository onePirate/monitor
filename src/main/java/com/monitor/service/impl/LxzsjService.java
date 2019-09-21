package com.monitor.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.monitor.common.constant.CommonConstant;
import com.monitor.dao.IJqRobotDao;
import com.monitor.dao.ILxzsjDao;
import com.monitor.entity.mpModel.JqRobotModel;
import com.monitor.entity.mpModel.LxzsjModel;
import com.monitor.service.IJqRobotService;
import com.monitor.service.ILxzsjService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LxzsjService extends ServiceImpl<ILxzsjDao, LxzsjModel> implements ILxzsjService {


    @Override
    public List<LxzsjModel> getInTimeDatas() {
        return baseMapper.getDeviceInTimeDatas();
    }
}
