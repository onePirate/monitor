package com.monitor.entity.mpModel;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("ssj")
public class SsjModel extends BaseDeviceModel{

    @TableField(exist = false)
    private String deviceType = "ssj";

}
