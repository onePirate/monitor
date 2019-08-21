package com.monitor.task;

import com.monitor.common.constant.CommonConstant;
import com.monitor.common.entity.TokenModel;
import com.monitor.common.tools.DateTool;
import com.monitor.common.tools.PointTypeBeanTool;
import com.monitor.common.tools.TokenTool;
import com.monitor.service.IDeviceCommonService;
import com.monitor.service.IWarnService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

@Slf4j
@Component
public class CleanOverdueEnvDataTask {

    @Value("${token.timeout}")
    long timeout;

    @Resource(name = "deviceCommonService")
    IDeviceCommonService deviceCommonService;

    @Autowired
    IWarnService warnService;

    @Scheduled(cron = "${jobs.cron}")
    public void envDataCleanTask() {
        String cleanTime = DateTool.getLastYearStringDate();
        log.info(cleanTime + " clean env data");
        try {
            PointTypeBeanTool.getMonitorService(CommonConstant.POINT_TYPE_ENVIRONMENT_TEMPERATURE).cleanEnvData(cleanTime);
        } catch (Exception e) {
            log.warn("ENVIRONMENT_TEMPERATURE clean data has errors", e);
        }
        try {
            PointTypeBeanTool.getMonitorService(CommonConstant.POINT_TYPE_ENVIRONMENT_HUMIDITY).cleanEnvData(cleanTime);
        } catch (Exception e) {
            log.warn("ENVIRONMENT_HUMIDITY clean data has errors", e);
        }
        try {
            PointTypeBeanTool.getMonitorService(CommonConstant.POINT_TYPE_CURVE_TEMPERATURE).cleanEnvData(cleanTime);
        } catch (Exception e) {
            log.warn("CURVE_TEMPERATURE clean data has errors", e);
        }
        try {
            PointTypeBeanTool.getMonitorService(CommonConstant.POINT_TYPE_CURVE_MOISTURE).cleanEnvData(cleanTime);
        } catch (Exception e) {
            log.warn("CURVE_MOISTURE clean data has errors", e);
        }
        try {
            PointTypeBeanTool.getMonitorService(CommonConstant.POINT_TYPE_O2_CONCENTRATION).cleanEnvData(cleanTime);
        } catch (Exception e) {
            log.warn("O2_CONCENTRATION clean data has errors", e);
        }
        try {
            PointTypeBeanTool.getMonitorService(CommonConstant.POINT_TYPE_CO2_CONCENTRATION).cleanEnvData(cleanTime);
        } catch (Exception e) {
            log.warn("CO2_CONCENTRATION clean data has errors", e);
        }
        try {
            deviceCommonService.cleanEnvData(cleanTime);
        } catch (Exception e) {
            log.warn("CO2_CONCENTRATION clean data has errors", e);
        }
        try {
            warnService.cleanEnvData(cleanTime);
        } catch (Exception e) {
            log.warn("CO2_CONCENTRATION clean data has errors", e);
        }
    }

    @Scheduled(cron = "${jobs.cron}")
    public void cleanToken() {
        try {
            Map<String, TokenModel> tokenModelMap = TokenTool.getTokenMap();
            for (Map.Entry<String, TokenModel> entry : tokenModelMap.entrySet()) {
                long curTime = System.currentTimeMillis();
                //如果超过指定token有限期，则退出登录
                if ((curTime-entry.getValue().getLoginTime())>timeout){
                    TokenTool.removeToken(entry.getKey());
                }
            }
        } catch (Exception e){
            log.warn("cleanToken has errors", e);
        }
    }

}
