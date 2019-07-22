package com.monitor.common.enums;

public enum StateEnum {

    OK(200,"成功"),
    FAIL(500,"失败"),
    REQ_HAS_ERR(506,"请求参数有误"),
    ;

    StateEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * code
     */
    private Integer code;

    /**
     * 中文释义
     */
    private String msg;

    /**
     * English Desc
     */
    private String enDesc;

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
    
}
