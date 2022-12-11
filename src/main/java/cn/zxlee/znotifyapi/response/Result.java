package cn.zxlee.znotifyapi.response;

import cn.zxlee.znotifyapi.enums.ResponseStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * @program: z-notify-api
 * @description: 响应类
 * @author: zxlee
 * @create: 2022-04-10 20:25
 **/

@Data
@Builder
@ApiModel("响应结果")
public class Result<T> {
    /**
     * response timestamp.
     */
    @ApiModelProperty("时间戳")
    private long timestamp;

    /**
     * response code, 0 -> OK.
     */
    @ApiModelProperty("状态，0代表成功")
    private int status;

    /**
     * response message.
     */
    @ApiModelProperty("状态信息")
    private String message;

    /**
     * response data.
     */
    @ApiModelProperty("响应数据")
    private T data;

    /**
     * response success result wrapper.
     *
     * @param <T> type of data class
     * @return response result
     */
    public static <T> Result<T> success() {
        return success(null);
    }

    /**
     * response success result wrapper.
     *
     * @param data response data
     * @param <T>  type of data class
     * @return response result
     */
    public static <T> Result<T> success(T data) {
        return Result.<T>builder().data(data)
                .message(ResponseStatus.SUCCESS.getMsg())
                .status(ResponseStatus.SUCCESS.getCode())
                .timestamp(System.currentTimeMillis())
                .build();
    }


    public static <T> Result<T> fail(int code, String message) {
        return Result.<T>builder().data(null)
                .message(message)
                .status(code)
                .timestamp(System.currentTimeMillis())
                .build();
    }

    /**
     * response error result wrapper.
     *
     * @param message error message
     * @param <T>     type of data class
     * @return response result
     */
    public static <T> Result<T> fail(String message) {
        return fail(ResponseStatus.SERVER_ERROR.getCode(), message);
    }
}
