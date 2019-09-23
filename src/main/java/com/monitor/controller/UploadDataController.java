package com.monitor.controller;

import com.monitor.common.constant.CommonConstant;
import com.monitor.common.entity.Result;
import com.monitor.common.enums.StateEnum;
import com.monitor.common.tools.PointTypeBeanTool;
import com.monitor.common.tools.ResultTool;
import com.monitor.entity.mpModel.*;
import com.monitor.entity.param.MonitorUploadParam;
import com.monitor.entity.param.WarnUploadParam;
import com.monitor.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
public class UploadDataController {


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

    @Autowired
    IWarnService warnService;

    /**
     * 查询工艺参数信息
     *
     * @param
     * @return
     */
    @PostMapping("/env/monitor/upload")
    public Result uploadMonitorInfoInTime(@RequestBody List<MonitorUploadParam> uploadParamList) {
        if (checkList(uploadParamList)) {
            return ResultTool.failed(StateEnum.REQ_HAS_ERR);
        }
        int count = 0;
        for (int i = 0; i < uploadParamList.size(); i++) {
            List<MonitorUploadParam> subParamList = new ArrayList<>();
            subParamList.add(uploadParamList.get(i));
            count += PointTypeBeanTool.getMonitorService(uploadParamList.get(i).getPointType()).batchInsert(subParamList);
        }
        return ResultTool.successWithMap(count);
    }

    /**
     * 查询设备状态信息
     *
     * @return
     */
    @PostMapping("/device/status/upload")
    public Result uploadDeviceStatus(@RequestBody List<DeviceStatusModel> uploadParamList) {
        if (checkList(uploadParamList)) {
            return ResultTool.failed(StateEnum.REQ_HAS_ERR);
        }
        //过滤垃圾数据，deviceType为null过滤
        List<DeviceStatusModel> allStatusDeviceList = uploadParamList.stream().filter(o -> {
            String deviceType = o.getDeviceType();
            if (StringUtils.isEmpty(deviceType)) {
                return false;
            }
            return true;
        }).collect(Collectors.toList());

        return ResultTool.successWithMap(execDeviceStatusInsert(allStatusDeviceList));
    }

    @Transactional
    public int execDeviceStatusInsert(List<DeviceStatusModel> allDeviceList) {
        if (allDeviceList != null && allDeviceList.size() > 0) {
            if (iDeviceStatusService.saveOrUpdateBatch(allDeviceList)) {
                return allDeviceList.size();
            }
        }
        return 0;
    }

    /**
     * 查询设备状态信息
     *
     * @return
     */
    @PostMapping("/allDevice/upload")
    public Result uploadAllDevice(@RequestBody List<BaseDeviceModel> uploadDeviceList) {
        if (checkList(uploadDeviceList)) {
            return ResultTool.failed(StateEnum.REQ_HAS_ERR);
        }
        //过滤垃圾数据，deviceType为null过滤
        List<BaseDeviceModel> baseDeviceModelList = uploadDeviceList.stream().filter(o -> {
            String deviceType = o.getDeviceType();
            if (StringUtils.isEmpty(deviceType)) {
                return false;
            }
            return true;
        }).collect(Collectors.toList());

        if (baseDeviceModelList == null || baseDeviceModelList.size() == 0) {
            ResultTool.failed(StateEnum.FAIL_SAVEDATA);
        }
        return ResultTool.successWithMap(execAllDeviceInsert(baseDeviceModelList));
    }

    @Transactional
    public int execAllDeviceInsert(List<BaseDeviceModel> baseDeviceModelList) {
        List<YqjModel> yqjModels = new ArrayList<>();
        List<SsjModel> ssjModels = new ArrayList<>();
        List<JqRobotModel> jqRobotModels = new ArrayList<>();
        List<LxzsjModel> lxzsjModels = new ArrayList<>();
        List<TpzyqgModel> tpzyqgModels = new ArrayList<>();

        baseDeviceModelList.stream().forEach(o -> {
            if (o.getDeviceType().equals(CommonConstant.YQJ)) {
                YqjModel yqjModel = new YqjModel();
                BeanUtils.copyProperties(o, yqjModel);
                yqjModels.add(yqjModel);
            }
            if (o.getDeviceType().equals(CommonConstant.SSJ)) {
                SsjModel ssjModel = new SsjModel();
                BeanUtils.copyProperties(o, ssjModel);
                ssjModels.add(ssjModel);
            }
            if (o.getDeviceType().equals(CommonConstant.JQ_ROBOT)) {
                JqRobotModel jqRobotModel = new JqRobotModel();
                BeanUtils.copyProperties(o, jqRobotModel);
                jqRobotModels.add(jqRobotModel);
            }
            if (o.getDeviceType().equals(CommonConstant.LXZSJ)) {
                LxzsjModel lxzsjModel = new LxzsjModel();
                BeanUtils.copyProperties(o, lxzsjModel);
                lxzsjModels.add(lxzsjModel);
            }
            if (o.getDeviceType().equals(CommonConstant.TPZYQG)) {
                TpzyqgModel tpzyqgModel = new TpzyqgModel();
                BeanUtils.copyProperties(o, tpzyqgModel);
                tpzyqgModels.add(tpzyqgModel);
            }
        });

        iYqjService.saveOrUpdateBatch(yqjModels);
        iSsjService.saveOrUpdateBatch(ssjModels);
        iJqRobotService.saveOrUpdateBatch(jqRobotModels);
        iLxzsjService.saveOrUpdateBatch(lxzsjModels);
        iTpzyqgService.saveOrUpdateBatch(tpzyqgModels);

        return baseDeviceModelList.size();
    }


    @PostMapping("/yqj/upload")
    public Result uploadYqjStatus(@RequestBody List<YqjModel> yqjModels) {
        if (checkList(yqjModels)) {
            return ResultTool.failed(StateEnum.REQ_HAS_ERR);
        }
        if (iYqjService.saveOrUpdateBatch(yqjModels)) {
            return ResultTool.successWithMap(yqjModels.size());
        }
        return ResultTool.failed(StateEnum.FAIL_SAVEDATA);
    }

    @PostMapping("/ssj/upload")
    public Result uploadSsjStatus(@RequestBody List<SsjModel> ssjModels) {
        if (checkList(ssjModels)) {
            return ResultTool.failed(StateEnum.REQ_HAS_ERR);
        }
        if (iSsjService.saveOrUpdateBatch(ssjModels)) {
            return ResultTool.successWithMap(ssjModels.size());
        }
        return ResultTool.failed(StateEnum.FAIL_SAVEDATA);
    }

    @PostMapping("/mdrobot/upload")
    public Result uploadRobotStatus(@RequestBody List<JqRobotModel> jqRobotModels) {
        if (checkList(jqRobotModels)) {
            return ResultTool.failed(StateEnum.REQ_HAS_ERR);
        }
        if (iJqRobotService.saveOrUpdateBatch(jqRobotModels)) {
            return ResultTool.successWithMap(jqRobotModels.size());
        }
        return ResultTool.failed(StateEnum.FAIL_SAVEDATA);
    }

    @PostMapping("/lxzsj/upload")
    public Result uploadLxzsjStatus(@RequestBody List<LxzsjModel> lxzsjModels) {
        if (checkList(lxzsjModels)) {
            return ResultTool.failed(StateEnum.REQ_HAS_ERR);
        }
        if (iLxzsjService.saveOrUpdateBatch(lxzsjModels)) {
            return ResultTool.successWithMap(lxzsjModels.size());
        }
        return ResultTool.failed(StateEnum.FAIL_SAVEDATA);
    }

    @PostMapping("/tpzyqg/upload")
    public Result uploadTpzyqgStatus(@RequestBody List<TpzyqgModel> tpzyqgModels) {
        if (checkList(tpzyqgModels)) {
            return ResultTool.failed(StateEnum.REQ_HAS_ERR);
        }
        if (iTpzyqgService.saveOrUpdateBatch(tpzyqgModels)) {
            return ResultTool.successWithMap(tpzyqgModels.size());
        }
        return ResultTool.failed(StateEnum.FAIL_SAVEDATA);
    }

    @PostMapping("/warns/upload")
    public Result uploadWarns(@RequestBody List<WarnUploadParam> warnUploadParams) {
        return ResultTool.successWithMap(warnService.insertWarns(warnUploadParams));
    }


    private boolean checkList(List list) {
        if (list == null || list.size() == 0)
            return true;
        return false;
    }

}
