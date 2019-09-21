package com.monitor.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.monitor.common.constant.CommonConstant;
import com.monitor.dao.ITpzyqgDao;
import com.monitor.dao.IYqjDao;
import com.monitor.entity.mpModel.TpzyqgModel;
import com.monitor.entity.mpModel.YqjModel;
import com.monitor.service.ITpzyqgService;
import com.monitor.service.IYqjService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class YqjService extends ServiceImpl<IYqjDao, YqjModel> implements IYqjService {
    @Override
    public List<YqjModel> getInTimeDatas() {
        return baseMapper.getDeviceInTimeDatas();
    }
}
