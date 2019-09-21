package com.monitor.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.monitor.entity.mpModel.YqjModel;

import java.util.List;

public interface IYqjDao extends BaseMapper<YqjModel> {

    List<YqjModel> getDeviceInTimeDatas();

}
