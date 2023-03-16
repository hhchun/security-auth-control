package com.cmzn.authcontrol.common.constant;


public enum ResultCodeConstant {
    SUCCESS(200, "成功"),
    ERROR(500, "系统错误"),
    FAIL(501, "失败"),
    AUTH(403, "无访问权限或token无效,请重新登录");


    ResultCodeConstant(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private Integer code;
    private String message;
}
