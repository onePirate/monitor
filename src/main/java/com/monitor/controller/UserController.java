package com.monitor.controller;

import com.alibaba.fastjson.JSONObject;
import com.monitor.common.entity.Result;
import com.monitor.common.enums.StateEnum;
import com.monitor.common.exception.CustomerException;
import com.monitor.common.tools.MD5Tool;
import com.monitor.common.tools.ResultTool;
import com.monitor.common.tools.TokenTool;
import com.monitor.dao.IUserDao;
import com.monitor.entity.model.UserModel;
import com.monitor.entity.param.UserParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class UserController {

    @Autowired
    IUserDao userDao;

    @PostMapping("login")
    public Result login(@RequestBody UserParam userParam){
        if (userParam == null
            || StringUtils.isEmpty(userParam.getPassword())
            || StringUtils.isEmpty(userParam.getUsername())) {
            throw new CustomerException(StateEnum.USER_HAS_ERR);
        }
        UserModel userModel = userDao.getUser(userParam);
        if (userModel == null){
            throw new CustomerException(StateEnum.USER_NOT_EXISTS);
        }
        String computeMD5 = MD5Tool.getMD5(userParam.getPassword());
        if (!userModel.getPassword().equals(computeMD5)){
            throw new CustomerException(StateEnum.USER_NOT_RIGHT);
        }
        String token = TokenTool.createToken(userParam);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("token",token);
        return ResultTool.successWithMap(jsonObject);
    }


}