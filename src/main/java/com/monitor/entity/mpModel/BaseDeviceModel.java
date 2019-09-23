package com.monitor.entity.mpModel;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

@Data
public class BaseDeviceModel implements Serializable {

    @TableId(type = IdType.AUTO)
    protected int id;

    @TableField("p_name")
    protected int pointName;

    @TableField("p_switch")
    protected int switchStatus;

    protected String collectTime;
    protected String modifyUser;
    protected String modifyTime;

    @TableField(exist = false)
    private String deviceType;

}
