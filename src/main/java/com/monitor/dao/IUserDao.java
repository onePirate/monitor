package com.monitor.dao;

import com.monitor.entity.model.UserModel;
import com.monitor.entity.param.UserParam;

public interface IUserDao {

    UserModel getUser(UserParam userParam);

    int insertUser(UserParam userParam);

}
