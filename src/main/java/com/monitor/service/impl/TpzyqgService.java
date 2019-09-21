package com.monitor.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.monitor.common.constant.CommonConstant;
import com.monitor.dao.ILxzsjDao;
import com.monitor.dao.ITpzyqgDao;
import com.monitor.entity.mpModel.LxzsjModel;
import com.monitor.entity.mpModel.TpzyqgModel;
import com.monitor.service.ILxzsjService;
import com.monitor.service.ITpzyqgService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TpzyqgService extends ServiceImpl<ITpzyqgDao, TpzyqgModel> implements ITpzyqgService {
    @Override
    public List<TpzyqgModel> getInTimeDatas() {
        return baseMapper.getDeviceInTimeDatas();
    }
}
