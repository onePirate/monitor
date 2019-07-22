package com.monitor.common.aspect;

import com.monitor.common.entity.Result;
import com.monitor.common.tools.ResultTool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.monitor.common.exception.CustomerException;

/**
 * @Author: gaodw
 * @Date: 20:29 2019/7/18
 * @Desc: 全局异常处理
 */
@Slf4j
@ControllerAdvice(annotations = RestController.class)
@ResponseBody
public class GlobalExceptionHandler {

    /**
     * 默认统一异常处理方法
     * @param e 默认CustomerException异常对象
     * @return
     */
    @ExceptionHandler(value = CustomerException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result runtimeExceptionHandler(CustomerException e) {
        return ResultTool.failed();
    }

    /**
     * 默认统一异常处理方法
     * @param e 默认CustomerException异常对象
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result runtimeExceptionHandler(Exception e) {
        log.error("server has error!",e);
        return ResultTool.failed();
    }


}
