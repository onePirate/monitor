package com.monitor.entity.mpModel;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("tpzyqg")
public class TpzyqgModel extends BaseDeviceModel{

    @TableField(exist = false)
    private String deviceType = "tpzyqg";

}
