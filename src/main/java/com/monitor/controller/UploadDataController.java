package com.monitor.controller;

import com.monitor.common.constant.CommonConstant;
import com.monitor.common.entity.Result;
import com.monitor.common.enums.StateEnum;
import com.monitor.common.tools.PointTypeBeanTool;
import com.monitor.common.tools.ResultTool;
import com.monitor.entity.param.*;
import com.monitor.service.IDeviceService;
import com.monitor.service.IWarnService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
public class UploadDataController {

    @Resource(name = "IDeviceStatusService")
    IDeviceService deviceStatusService;

    @Resource(name = "IDeviceSwitchService")
    IDeviceService deviceSwitchService;

    @Resource(name = "IDeviceCommonService")
    IDeviceService deviceCommonService;

    @Autowired
    IWarnService warnService;

    /**
     * 查询工艺参数信息
     * @param
     * @return
     */
    @PostMapping("/env/monitor/upload")
    public Result uploadMonitorInfoInTime(@RequestBody List<MonitorUploadParam> uploadParamList){
        if (checkList(uploadParamList)){
            return ResultTool.failed(StateEnum.REQ_HAS_ERR);
        }
        return ResultTool.successWithMap(PointTypeBeanTool.getMonitorService(uploadParamList.get(0).getPointType()).batchInsert(uploadParamList));
    }

    /**
     * 查询设备状态信息
     * @return
     */
    @PostMapping("/device/status/upload")
    public Result uploadDeviceStatus(@RequestBody List<MonitorUploadParam> uploadParamList){
        if (checkList(uploadParamList)){
            return ResultTool.failed(StateEnum.REQ_HAS_ERR);
        }
        //过滤垃圾数据，deviceType为null过滤
        List<MonitorUploadParam> allDeviceList = uploadParamList.stream().filter(o -> {
            String deviceType = o.getDeviceType();
            if (StringUtils.isEmpty(deviceType)){
                return false;
            }
            return true;
        }).collect(Collectors.toList());

        List<MonitorUploadParam> statusList = allDeviceList.stream().filter(o -> {
            String deviceType = o.getDeviceType();
            if (CommonConstant.DEVICE_TEMPERATURE.equals(deviceType) || CommonConstant.DEVICE_HUMIDITY.equals(deviceType) ){
                return true;
            }
            return false;
        }).collect(Collectors.toList());

        allDeviceList.removeAll(statusList);

        return ResultTool.successWithMap(execDeviceInsert(allDeviceList, statusList));
    }

    @Transactional
    public int execDeviceInsert(List<MonitorUploadParam> allDeviceList, List<MonitorUploadParam> statusList) {
        int count = 0;
        count += deviceStatusService.batchInsert(CommonConstant.TABLE_DEVICE_STATUS,statusList);
        count += deviceSwitchService.batchInsert(CommonConstant.TABLE_DEVICE_SWITCH,allDeviceList);
        return count;
    }


    @PostMapping("/agv/upload")
    public Result uploadAgvStatus(@RequestBody List<AgvUploadParam> agvUploadParams){
        if (checkList(agvUploadParams)){
            return ResultTool.failed(StateEnum.REQ_HAS_ERR);
        }
        return ResultTool.successWithMap(deviceCommonService.batchInsert(CommonConstant.TABLE_AGV,agvUploadParams));
    }


    @PostMapping("/yqj/upload")
    public Result uploadYqjStatus(@RequestBody List<YqjUploadParam> yqjUploadParams){
        if (checkList(yqjUploadParams)){
            return ResultTool.failed(StateEnum.REQ_HAS_ERR);
        }
        return ResultTool.successWithMap(deviceCommonService.batchInsert(CommonConstant.TABLE_YQJ,yqjUploadParams));
    }


    @PostMapping("/rlj/upload")
    public Result uploadRljStatus(@RequestBody List<RljUploadParam> rljUploadParams){
        if (checkList(rljUploadParams)){
            return ResultTool.failed(StateEnum.REQ_HAS_ERR);
        }
        return ResultTool.successWithMap(deviceCommonService.batchInsert(CommonConstant.TABLE_RLJ,rljUploadParams));
    }


    @PostMapping("/ssj/upload")
    public Result uploadSsjStatus(@RequestBody List<SsjUploadParam> ssjUploadParams){
        if (checkList(ssjUploadParams)){
            return ResultTool.failed(StateEnum.REQ_HAS_ERR);
        }
        return ResultTool.successWithMap(deviceCommonService.batchInsert(CommonConstant.TABLE_SSJ,ssjUploadParams));
    }


    @PostMapping("/mdrobot/upload")
    public Result uploadMdRobotStatus(@RequestBody List<MonitorUploadParam> mdRobotUploadParams){
        if (checkList(mdRobotUploadParams)){
            return ResultTool.failed(StateEnum.REQ_HAS_ERR);
        }
        return ResultTool.successWithMap(deviceCommonService.batchInsert(CommonConstant.TABLE_MD_ROBOT,mdRobotUploadParams));
    }

    @PostMapping("/warns/upload")
    public Result uploadWarns(@RequestBody List<WarnUploadParam> warnUploadParams){
        return ResultTool.successWithMap(warnService.insertWarns(warnUploadParams));
    }


    private boolean checkList(List list) {
        if (list == null || list.size()==0)
            return true;
        return false;
    }

}
