package cn.zxlee.znotifyapi.pojo.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

/**
 * @program: z-notify-api
 * @description: 版本BO
 * @author: zxlee
 * @create: 2022-12-11 16:01
 **/

@Data
@ApiModel("版本BO")
public class VersionBO {
    @ApiModelProperty("版本号")
    @NotEmpty(message = "版本号不能为空")
    @Pattern(regexp = "^\\d(.\\d)*$", message = "版本号格式不合法")
    private String version;

    @ApiModelProperty("更新内容")
    private String content;

    @ApiModelProperty("下载地址")
    private String downloadUrl;

    @ApiModelProperty("项目id")
    @NotEmpty(message = "项目id不能为空")
    private String projectId;
}
