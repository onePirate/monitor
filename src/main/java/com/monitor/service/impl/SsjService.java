package com.monitor.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.monitor.common.constant.CommonConstant;
import com.monitor.dao.ILxzsjDao;
import com.monitor.dao.ISsjDao;
import com.monitor.entity.mpModel.LxzsjModel;
import com.monitor.entity.mpModel.SsjModel;
import com.monitor.service.ILxzsjService;
import com.monitor.service.ISsjService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SsjService extends ServiceImpl<ISsjDao, SsjModel> implements ISsjService {
    @Override
    public List<SsjModel> getInTimeDatas() {
        return baseMapper.getDeviceInTimeDatas();
    }
}
