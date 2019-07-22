package com.monitor.controller;

import com.monitor.common.entity.Result;
import com.monitor.common.enums.StateEnum;
import com.monitor.common.tools.ResultTool;
import com.monitor.entity.param.WarnHistoryParam;
import com.monitor.service.IWarnService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class WarnController {

    @Autowired
    IWarnService warnService;

    @PostMapping("/warns/query/intime")
    public Result queryWarnInfoInTime(){
        return ResultTool.successWithMap(warnService.getInTimeWarns());
    }

    @PostMapping("/warns/query/history")
    public Result queryWarnInfoHistory(@RequestBody WarnHistoryParam warnHistoryParam){
        if (warnHistoryParam==null || StringUtils.isEmpty(warnHistoryParam.getStartTime())
                ||  StringUtils.isEmpty(warnHistoryParam.getEndTime())){
            return ResultTool.failed(StateEnum.REQ_HAS_ERR);
        }
        return ResultTool.successWithMap(warnService.getHistoryWarns(warnHistoryParam));
    }

}
