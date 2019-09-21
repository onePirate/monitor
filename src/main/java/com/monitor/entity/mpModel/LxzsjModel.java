package com.monitor.entity.mpModel;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("lxzsj")
public class LxzsjModel implements Serializable {

    /**
     * 主键ID, 数据库ID自增
     */
    @TableId(type = IdType.AUTO)
    private int id;

    @TableField("p_name")
    private int pointName;

    @TableField("p_switch")
    private int switchStatus;


    private String collectTime;
    private String modifyUser;
    private String modifyTime;

}
