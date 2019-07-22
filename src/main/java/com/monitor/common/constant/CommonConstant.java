package com.monitor.common.constant;

public class CommonConstant {

    /**
     * 查询点位类型
     * 1: 环境温度
     * 2: 环境湿度
     * 3: 曲块温度
     * 4: 曲块水分
     * 5: O2浓度
     * 6: CO2浓度
     */
    public static final int POINT_TYPE_ENVIRONMENT_TEMPERATURE = 1;
    public static final int POINT_TYPE_ENVIRONMENT_HUMIDITY = 2;
    public static final int POINT_TYPE_CURVE_TEMPERATURE = 3;
    public static final int POINT_TYPE_CURVE_MOISTURE = 4;
    public static final int POINT_TYPE_O2_CONCENTRATION = 5;
    public static final int POINT_TYPE_CO2_CONCENTRATION = 6;

    /**
     * 发酵房设备
     */
    public static final String DEVICE_BLOWER = "blower";//风机
    public static final String DEVICE_HEATER_= "heater";//加热器
    public static final String DEVICE_TEMPERATURE = "temperature";//加热器
    public static final String DEVICE_HUMIDIFIER = "humidifier";//加湿器
    public static final String DEVICE_HUMIDITY = "humidity";//湿度
    public static final String DEVICE_BLIND = "blind";//百叶窗
    public static final String DEVICE_AUTO_DOOR = "auto_door";//自动门
    public static final String DEVICE_CYCLE_MODE = "cycle_mode";//循环模式(0:内\1:外)

    /**
     * 表名
     */
    public static final String TABLE_CO2_CONCENTRATION = "co2_concentration";
    public static final String TABLE_CURVE_MOISTURE = "curve_moisture";
    public static final String TABLE_CURVE_TEMPERATURE = "curve_temperature";
    public static final String TABLE_ENV_HUMIDITY = "environment_humidity";
    public static final String TABLE_ENV_TEMPERATURE = "environment_temperature";
    public static final String TABLE_O2_CONCENTRATION= "o2_concentration";
    public static final String TABLE_DEVICE_SWITCH = "device_switch";
    public static final String TABLE_DEVICE_STATUS = "device_status";
    public static final String TABLE_AGV = "agv";
    public static final String TABLE_MD_ROBOT = "md_robot";
    public static final String TABLE_RLJ = "rlj";
    public static final String TABLE_SSJ = "ssj";
    public static final String TABLE_YQJ = "yqj";




}
