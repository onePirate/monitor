package com.monitor.service;

public interface IDeviceService {

    Object getDeviceInTimeStatus(String tableName);

    int batchInsert(String tableName,Object uploadParamList);

}
