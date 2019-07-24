package com.monitor.controller;

import com.alibaba.fastjson.JSONArray;
import com.monitor.common.constant.CommonConstant;
import com.monitor.common.entity.Result;
import com.monitor.common.enums.StateEnum;
import com.monitor.common.tools.PointTypeBeanTool;
import com.monitor.common.tools.ResultTool;
import com.monitor.entity.model.CommonMonitorModel;
import com.monitor.entity.model.DeviceCommonModel;
import com.monitor.entity.model.DeviceStatusModel;
import com.monitor.entity.param.EnvQueryHistoryParam;
import com.monitor.entity.param.EnvQueryInTimeParam;
import com.monitor.service.IDeviceCommonService;
import com.monitor.service.IDeviceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
public class MonitorController {

    @Resource(name = "deviceStatusService")
    IDeviceService deviceStatusService;

    @Resource(name = "deviceSwitchService")
    IDeviceService deviceSwitchService;

    @Resource(name = "deviceCommonService")
    IDeviceCommonService deviceCommonService;

    /**
     * 查询工艺参数信息
     * @param envQueryInTimeParam
     * @return
     */
    @PostMapping("/env/monitor/query/intime")
    public Result queryMonitorInfoInTime(@RequestBody EnvQueryInTimeParam envQueryInTimeParam){
        if (envQueryInTimeParam ==null || StringUtils.isEmpty(envQueryInTimeParam.getPointType())){
            return ResultTool.failed(StateEnum.REQ_HAS_ERR);
        }
        return ResultTool.successWithMap(PointTypeBeanTool.getMonitorService(envQueryInTimeParam.getPointType()).getEnvMonitorInTimeQuery(envQueryInTimeParam));
    }

    /**
     * 查询工艺参数信息
     * @param envQueryHistoryParam
     * @return
     */
    @PostMapping("/env/monitor/query/history")
    public Result queryMonitorInfoHistory(@RequestBody EnvQueryHistoryParam envQueryHistoryParam){
        if (envQueryHistoryParam ==null || StringUtils.isEmpty(envQueryHistoryParam.getPointType())
                || StringUtils.isEmpty(envQueryHistoryParam.getPageIndex())
                || StringUtils.isEmpty(envQueryHistoryParam.getPageSize())
                || StringUtils.isEmpty(envQueryHistoryParam.getStartTime())
                || StringUtils.isEmpty(envQueryHistoryParam.getStartTime())){
            return ResultTool.failed(StateEnum.REQ_HAS_ERR);
        }
        List<CommonMonitorModel> monitorModels = PointTypeBeanTool.getMonitorService(envQueryHistoryParam.getPointType()).getEnvMonitorHistoryQuery(envQueryHistoryParam);
        List<Integer> pointList = monitorModels.stream().map(o -> o.getPointName()).distinct().collect(Collectors.toList());
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < pointList.size(); i++) {
            List<CommonMonitorModel> subMonitorModels = new ArrayList<>();
            for (int j = 0; j < monitorModels.size(); j++) {
                if (pointList.get(i) == monitorModels.get(j).getPointName()){
                    subMonitorModels.add(monitorModels.get(j));
                }
            }
            jsonArray.add(ResultTool.successWithList(subMonitorModels,envQueryHistoryParam.getPageIndex(),envQueryHistoryParam.getPageSize()));
        }
        return ResultTool.successWithMap(jsonArray);
    }


    /**
     * 查询设备状态信息
     * @return
     */
    @PostMapping("/device/status")
    public Result queryDeviceStatus(){
        List<DeviceStatusModel> deviceStatusModelList = (List<DeviceStatusModel>)deviceSwitchService.getDeviceInTimeStatus(CommonConstant.TABLE_DEVICE_SWITCH);
        deviceStatusModelList.addAll((List<DeviceStatusModel>)deviceStatusService.getDeviceInTimeStatus(CommonConstant.TABLE_DEVICE_STATUS));
        return ResultTool.successWithMap(deviceStatusModelList);
    }

    /**
     * 查询AGV开关状态信息
     * @return
     */
    @PostMapping("/agv/switch")
    public Result queryAgvStatus(){
        return ResultTool.successWithMap(deviceCommonService.getDeviceInTimeStatus(CommonConstant.TABLE_AGV));
    }

    /**
     * 查询压曲机开关状态信息
     * @return
     */
    @PostMapping("/yqj/switch")
    public Result queryYqjStatus(){
        return ResultTool.successWithMap(deviceCommonService.getDeviceInTimeStatus(CommonConstant.TABLE_YQJ));
    }

    /**
     * 查询润粮机、输送机、码垛机器人开关状态信息
     * @return
     */
    @PostMapping("/therest/switch")
    public Result queryRljAndSsjAndRobotStatus(){
        List<DeviceCommonModel> deviceCommonModelList = (List<DeviceCommonModel>)deviceCommonService.getDeviceInTimeStatus(CommonConstant.TABLE_RLJ);

        //返回输送机信息状态信息
        deviceCommonModelList.addAll((List<DeviceCommonModel>)deviceCommonService.getDeviceInTimeStatus(CommonConstant.TABLE_SSJ));
        //返回码垛机器人状态信息
        deviceCommonModelList.addAll((List<DeviceCommonModel>)deviceCommonService.getDeviceInTimeStatus(CommonConstant.TABLE_MD_ROBOT));
        return ResultTool.successWithMap(deviceCommonModelList);
    }


}
