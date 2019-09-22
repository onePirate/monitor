package com.monitor.controller;

import com.alibaba.fastjson.JSONArray;
import com.monitor.common.entity.Result;
import com.monitor.common.enums.StateEnum;
import com.monitor.common.tools.PointTypeBeanTool;
import com.monitor.common.tools.ResultTool;
import com.monitor.entity.model.CommonMonitorModel;
import com.monitor.entity.param.DeviceRoomParam;
import com.monitor.entity.param.EnvQueryHistoryParam;
import com.monitor.entity.param.EnvQueryInTimeParam;
import com.monitor.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
public class DeviceQueryController {


    @Autowired
    IDeviceStatusService iDeviceStatusService;
    @Autowired
    IYqjService iYqjService;
    @Autowired
    ISsjService iSsjService;
    @Autowired
    ITpzyqgService iTpzyqgService;
    @Autowired
    ILxzsjService iLxzsjService;
    @Autowired
    IJqRobotService iJqRobotService;

    /**
     * @param envQueryInTimeParam
     * @return
     */
    @PostMapping("/env/monitor/query/intime")
    public Result queryMonitorInfoInTime(@RequestBody EnvQueryInTimeParam envQueryInTimeParam) {
        if (envQueryInTimeParam == null || StringUtils.isEmpty(envQueryInTimeParam.getPointType())) {
            return ResultTool.failed(StateEnum.REQ_HAS_ERR);
        }
        return ResultTool.successWithMap(PointTypeBeanTool.getMonitorService(envQueryInTimeParam.getPointType()).getEnvMonitorInTimeQuery(envQueryInTimeParam));
    }

    /**
     * @param envQueryHistoryParam
     * @return
     */
    @PostMapping("/env/monitor/query/history")
    public Result queryMonitorInfoHistory(@RequestBody EnvQueryHistoryParam envQueryHistoryParam) {
        if (envQueryHistoryParam == null || StringUtils.isEmpty(envQueryHistoryParam.getPointType())
                || StringUtils.isEmpty(envQueryHistoryParam.getPageIndex())
                || StringUtils.isEmpty(envQueryHistoryParam.getPageSize())
                || StringUtils.isEmpty(envQueryHistoryParam.getStartTime())
                || StringUtils.isEmpty(envQueryHistoryParam.getStartTime())) {
            return ResultTool.failed(StateEnum.REQ_HAS_ERR);
        }
        List<CommonMonitorModel> monitorModels = PointTypeBeanTool.getMonitorService(envQueryHistoryParam.getPointType()).getEnvMonitorHistoryQuery(envQueryHistoryParam);
        List<Integer> pointList = monitorModels.stream().map(o -> o.getPointName()).distinct().collect(Collectors.toList());
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < pointList.size(); i++) {
            List<CommonMonitorModel> subMonitorModels = new ArrayList<>();
            for (int j = 0; j < monitorModels.size(); j++) {
                if (pointList.get(i) == monitorModels.get(j).getPointName()) {
                    subMonitorModels.add(monitorModels.get(j));
                }
            }
            jsonArray.add(ResultTool.successWithList(subMonitorModels, envQueryHistoryParam.getPageIndex(), envQueryHistoryParam.getPageSize()));
        }
        return ResultTool.successWithMap(jsonArray);
    }

    @PostMapping("/device/status")
    public Result getRoomInTimeData(@RequestBody DeviceRoomParam deviceRoomParam) {
        if (deviceRoomParam == null || deviceRoomParam.getFunctionRoom() == null) {
            return ResultTool.failed(StateEnum.REQ_HAS_ERR);
        }
        return ResultTool.successWithMap(iDeviceStatusService.getInTimeData(deviceRoomParam.getFunctionRoom()));
    }

    @PostMapping("/allDevice/switch")
    public Result getAllDeviceInTimeData() {
        List<Object> allDeviceList = new ArrayList<>();
        try {
            allDeviceList.addAll(iYqjService.getInTimeDatas());
        } catch (Exception ex) {
            log.warn("yqj in time data has errors!", ex);
        }
        try {
            allDeviceList.addAll(iSsjService.getInTimeDatas());
        } catch (Exception ex) {
            log.warn("ssj in time data has errors!", ex);
        }
        try {
            allDeviceList.addAll(iJqRobotService.getInTimeDatas());
        } catch (Exception ex) {
            log.warn("JqRobot in time data has errors!", ex);
        }
        try {
            allDeviceList.addAll(iTpzyqgService.getInTimeDatas());
        } catch (Exception ex) {
            log.warn("tpzyqg in time data has errors!", ex);
        }
        try {
            allDeviceList.addAll(iLxzsjService.getInTimeDatas());
        } catch (Exception ex) {
            log.warn("lxzsj in time data has errors!", ex);
        }
        return ResultTool.successWithMap(allDeviceList);
    }

    @PostMapping("/yqj/switch")
    public Result getYqjInTimeData() {
        return ResultTool.successWithMap(iYqjService.getInTimeDatas());
    }

    @PostMapping("/ssj/switch")
    public Result getSsjInTimeData() {
        return ResultTool.successWithMap(iSsjService.getInTimeDatas());
    }

    @PostMapping("/jqRobot/switch")
    public Result getJqRobotInTimeData() {
        return ResultTool.successWithMap(iJqRobotService.getInTimeDatas());
    }

    @PostMapping("/lxzsj/switch")
    public Result getLxzsjInTimeData() {
        return ResultTool.successWithMap(iLxzsjService.getInTimeDatas());
    }

    @PostMapping("/tpzyqg/switch")
    public Result getTpzyqgInTimeData() {
        return ResultTool.successWithMap(iTpzyqgService.getInTimeDatas());
    }


}
