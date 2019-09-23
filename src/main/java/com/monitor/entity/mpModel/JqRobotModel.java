package com.monitor.entity.mpModel;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("jq_robot")
public class JqRobotModel extends BaseDeviceModel{

    @TableField(exist = false)
    private String deviceType = "jqRobot";

}
