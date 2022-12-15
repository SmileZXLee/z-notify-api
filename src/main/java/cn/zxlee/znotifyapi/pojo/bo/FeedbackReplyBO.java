package cn.zxlee.znotifyapi.pojo.bo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;


/**
 * @program: z-notify-api
 * @description: 反馈回复BO
 * @author: zxlee
 * @create: 2022-12-13 19:18
 **/

@Data
public class FeedbackReplyBO {
    @ApiModelProperty("回复内容")
    @NotEmpty(message = "回复内容不能为空")
    private String reply;

}
