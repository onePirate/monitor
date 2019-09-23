package com.monitor.entity.mpModel;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("yqj")
public class YqjModel extends BaseDeviceModel{

    @TableField(exist = false)
    private String deviceType = "yqj";

}
