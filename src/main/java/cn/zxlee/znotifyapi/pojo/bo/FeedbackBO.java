package cn.zxlee.znotifyapi.pojo.bo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @program: z-notify-api
 * @description: 反馈BO
 * @author: zxlee
 * @create: 2022-12-13 19:18
 **/

@Data
public class FeedbackBO {
    @ApiModelProperty("反馈者用户名")
    @NotEmpty(message = "反馈者用户名不能为空")
    private String username;

    @ApiModelProperty("反馈者联系方式")
    private String contact;

    @ApiModelProperty("反馈内容")
    @NotEmpty(message = "反馈内容不能为空")
    private String content;

    @ApiModelProperty("附加信息，例如设备信息，应用版本等")
    private String extraInfo;

    @ApiModelProperty("反馈图片地址")
    @Size(max = 6, message = "反馈图片不能超过6张")
    private List<String> imgUrls;

    @ApiModelProperty("项目id")
    @NotEmpty(message = "项目id不能为空")
    private String projectId;
}
