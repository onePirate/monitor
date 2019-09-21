package com.monitor.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.monitor.entity.mpModel.JqRobotModel;

import java.util.List;

public interface IJqRobotDao extends BaseMapper<JqRobotModel> {

    List<JqRobotModel> getDeviceInTimeDatas();


}
