package cn.zxlee.znotifyapi.pojo.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

/**
 * @program: z-notify-api
 * @description: 通知BO
 * @author: zxlee
 * @create: 2022-11-25 21:47
 **/

@Data
@ApiModel("通知BO")
public class NoticeBO {
    @ApiModelProperty("通知标题")
    @NotEmpty(message = "通知标题不能为空")
    private String title;

    @ApiModelProperty("通知内容")
    @NotEmpty(message = "通知内容不能为空")
    private String content;

    @ApiModelProperty("项目id")
    @NotEmpty(message = "项目id不能为空")
    private String projectId;

    @ApiModelProperty(value = "过期时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date expiretime;
}
