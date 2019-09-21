package com.monitor.entity.mpModel;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableName("user")
public class UserModel {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String username;

    private String password;

    private String role;
}
