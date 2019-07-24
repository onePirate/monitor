package com.monitor.task;

import com.monitor.common.constant.CommonConstant;
import com.monitor.common.tools.DateTool;
import com.monitor.common.tools.PointTypeBeanTool;
import com.monitor.service.IDeviceCommonService;
import com.monitor.service.IWarnService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Slf4j
@Component
public class CleanOverdueEnvDataTask {

    @Resource(name = "deviceCommonService")
    IDeviceCommonService deviceCommonService;

    @Autowired
    IWarnService warnService;

    @Scheduled(cron = "${jobs.cron}")
    public void envDataCleanTask() {
        String cleanTime = DateTool.getLastYearStringDate();
        log.info(cleanTime+" clean env data" );
        try{
            PointTypeBeanTool.getMonitorService(CommonConstant.POINT_TYPE_ENVIRONMENT_TEMPERATURE).cleanEnvData(cleanTime);
        }catch (Exception e){
            log.info("ENVIRONMENT_TEMPERATURE clean data has errors",e);
        }
        try{
            PointTypeBeanTool.getMonitorService(CommonConstant.POINT_TYPE_ENVIRONMENT_HUMIDITY).cleanEnvData(cleanTime);
        }catch (Exception e){
            log.info("ENVIRONMENT_HUMIDITY clean data has errors",e);
        }
        try{
            PointTypeBeanTool.getMonitorService(CommonConstant.POINT_TYPE_CURVE_TEMPERATURE).cleanEnvData(cleanTime);
        }catch (Exception e){
            log.info("CURVE_TEMPERATURE clean data has errors",e);
        }
        try{
            PointTypeBeanTool.getMonitorService(CommonConstant.POINT_TYPE_CURVE_MOISTURE).cleanEnvData(cleanTime);
        }catch (Exception e){
            log.info("CURVE_MOISTURE clean data has errors",e);
        }
        try{
            PointTypeBeanTool.getMonitorService(CommonConstant.POINT_TYPE_O2_CONCENTRATION).cleanEnvData(cleanTime);
        }catch (Exception e){
            log.info("O2_CONCENTRATION clean data has errors",e);
        }
        try{
            PointTypeBeanTool.getMonitorService(CommonConstant.POINT_TYPE_CO2_CONCENTRATION).cleanEnvData(cleanTime);
        }catch (Exception e){
            log.info("CO2_CONCENTRATION clean data has errors",e);
        }
        try{
            deviceCommonService.cleanEnvData(cleanTime);
        }catch (Exception e){
            log.info("CO2_CONCENTRATION clean data has errors",e);
        }
        try{
            warnService.cleanEnvData(cleanTime);
        }catch (Exception e){
            log.info("CO2_CONCENTRATION clean data has errors",e);
        }
    }

}
