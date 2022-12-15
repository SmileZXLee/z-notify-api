package cn.zxlee.znotifyapi.pojo.vo;

import cn.zxlee.znotifyapi.pojo.BasePOJO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @program: z-notify-api
 * @description: FeedbackVO
 * @author: zxlee
 * @create: 2022-12-13 19:18
 **/

@Data
@ApiModel("反馈VO")
public class FeedbackVO extends BasePOJO {
    @ApiModelProperty("反馈者用户名")
    private String username;
    @ApiModelProperty("反馈者联系方式")
    private String contact;
    @ApiModelProperty("反馈内容")
    private String content;
    @ApiModelProperty("附加信息，例如设备信息，应用版本等")
    private String extraInfo;
    @ApiModelProperty("反馈图片地址")
    private List imgUrls;
    @ApiModelProperty("回复内容")
    private String reply;
    @ApiModelProperty("项目id")
    private String projectId;
}
