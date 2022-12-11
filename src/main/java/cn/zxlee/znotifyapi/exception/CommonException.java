package cn.zxlee.znotifyapi.exception;

/**
 * @program: z-notify-api
 * @description:
 * @author: zxlee
 * @create: 2022-11-26 23:36
 **/
public class CommonException extends RuntimeException {
    public CommonException() {
        super("系统异常，操作失败");
    }

    public CommonException(String message) {
        super(message);
    }
}
