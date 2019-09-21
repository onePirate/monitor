package com.monitor.service;

import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface ICustomService<T> extends IService<T> {

    List<T> getInTimeDatas();

}
