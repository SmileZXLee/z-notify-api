package cn.zxlee.znotifyapi.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseStatus {

    SUCCESS(0, "ok"),
    FAIL(500, "failed"),
    USER_PARAM_ERROR(10001, "参数错误"),
    USER_LOGIN_AUTH_ERROR(10002, "权限不足，请先登录"),
    SERVER_ERROR(20001, "服务器异常");


    /**
     * response code
     */
    private final int code;

    /**
     * message.
     */
    private final String msg;

}
