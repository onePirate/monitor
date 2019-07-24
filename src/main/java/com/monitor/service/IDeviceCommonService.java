package com.monitor.service;

public interface IDeviceCommonService {

    Object getDeviceInTimeStatus(String tableName);

    int batchInsert(String tableName, Object uploadParamList);

    void cleanEnvData(String cleanTime);

}
