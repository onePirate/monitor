package com.monitor.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.monitor.entity.mpModel.LxzsjModel;

import java.util.List;

public interface ILxzsjDao extends BaseMapper<LxzsjModel> {

    List<LxzsjModel> getDeviceInTimeDatas();


}
