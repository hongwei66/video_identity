package com.longma.videoidenty.common;

import org.springframework.util.StringUtils;

/**
 * @Auther: liuzhenyun
 * @Date: 2019-05-02 15:16
 * @Description:
 */
public enum SystemErrorEnum {
    SYS_NOLOGIN_ERROR("9999", "no login,please login first."),
    SYS_REDIRECT_GET("9998", "http get response redirect."),
    SYS_REDIRECT_POST("9997", "http post response redirect."),
    SYSTEM_ERROR("9001", "系统异常，请稍后再试"),
    TOO_MUCH_ERROR("9999", "系统限流，请稍后再试"),
    SYS_PARAM_ERROR("1000", "参数错误"),
    SSO_AUTHORIZE_ERROR("1001", "认证失败"),
    SSO_AUTHORIZE_CANNOT_ERROR("1002", "权限不足"),
    SSO_TOKEN_INVALID_ERROR("1003", "token不存在或无效"),
    SSO_UNAUTHORIZEDCLIENT_ERROR("1004", "未认证的客户端"),
    SSO_UNAUTHORIZEDUSER_ERROR("1005", "未认证的用户"),
    SSO_PHONE_SMSCODE_ERROR("1006", "手机号或验证码不能为空"),
    SSO_SCAN_REPEAT_ERROR("1007", "重复扫码"),
    SSO_USER_ERROR("1008", "用户名或密码错误"),
    DB_OPTION_ERROR("1111", "数据库错误"),
    DB_DATA_NOT_FOUND("1112", "未查询到相关数据"),
    LOG_ERROR("7001", "日志Aspect错误"),

    HTTP_REQUEST_ERR("8001", "HTTP 请求异常"),

    UNKNOWN_ERROR("10000", "未知异常");

    private String msg;
    private String code;

    private SystemErrorEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static SystemErrorEnum getByCode(String code) {
        if (StringUtils.isEmpty(code)) {
            return null;
        } else {
            SystemErrorEnum[] var1 = values();
            int var2 = var1.length;

            for (int var3 = 0; var3 < var2; ++var3) {
                SystemErrorEnum errorEnum = var1[var3];
                if (errorEnum.getCode().equals(code)) {
                    return errorEnum;
                }
            }

            return null;
        }
    }

    public String getMsg() {
        return this.msg;
    }

    public String getCode() {
        return this.code;
    }
}
