package com.monitor.entity.mpModel;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("lxzsj")
public class LxzsjModel extends BaseDeviceModel{

    @TableField(exist = false)
    private String deviceType = "lxzsj";

}
