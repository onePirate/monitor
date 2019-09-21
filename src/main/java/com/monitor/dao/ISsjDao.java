package com.monitor.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.monitor.entity.mpModel.SsjModel;

import java.util.List;

public interface ISsjDao extends BaseMapper<SsjModel> {
    List<SsjModel> getDeviceInTimeDatas();


}
