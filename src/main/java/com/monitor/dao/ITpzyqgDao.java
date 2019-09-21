package com.monitor.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.monitor.entity.mpModel.TpzyqgModel;

import java.util.List;

public interface ITpzyqgDao extends BaseMapper<TpzyqgModel> {

    List<TpzyqgModel> getDeviceInTimeDatas();
}
