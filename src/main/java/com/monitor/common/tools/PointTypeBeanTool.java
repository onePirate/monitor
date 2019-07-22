package com.monitor.common.tools;

import com.monitor.common.constant.CommonConstant;
import com.monitor.service.IMonitorService;

import java.util.HashMap;
import java.util.Map;

public class PointTypeBeanTool {

    private static Map<Integer, String> pointTypeBeanMap = new HashMap<>();
    static {
        pointTypeBeanMap.put(CommonConstant.POINT_TYPE_ENVIRONMENT_TEMPERATURE,"environmentTemperatureService");
        pointTypeBeanMap.put(CommonConstant.POINT_TYPE_ENVIRONMENT_HUMIDITY,"environmentHumidityService");
        pointTypeBeanMap.put(CommonConstant.POINT_TYPE_CURVE_TEMPERATURE,"curveTemperatureService");
        pointTypeBeanMap.put(CommonConstant.POINT_TYPE_CURVE_MOISTURE,"curveMoistureService");
        pointTypeBeanMap.put(CommonConstant.POINT_TYPE_O2_CONCENTRATION,"o2ConcentrationService");
        pointTypeBeanMap.put(CommonConstant.POINT_TYPE_CO2_CONCENTRATION,"co2ConcentrationService");
    }

    public static IMonitorService getMonitorService(int pointType){
        return BeanTool.getBean(pointTypeBeanMap.get(pointType));
    }

}
