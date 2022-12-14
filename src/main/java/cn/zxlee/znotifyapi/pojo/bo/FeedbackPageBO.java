package cn.zxlee.znotifyapi.pojo.bo;

import cn.zxlee.znotifyapi.pojo.bo.base.PageBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: z-notify-api
 * @description: 通知分页BO
 * @author: zxlee
 * @create: 2022-11-27 00:22
 **/

@Data
@ApiModel("反馈分页BO")
public class FeedbackPageBO extends PageBO {
    @ApiModelProperty("标题搜索关键字")
    private String keyword;
}
