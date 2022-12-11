package cn.zxlee.znotifyapi.pojo.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @program: z-notify-api
 * @description: 文本BO
 * @author: zxlee
 * @create: 2022-12-07 19:40
 **/

@Data
@ApiModel("文本BO")
public class TextBO {
    @ApiModelProperty("文本key")
    @NotEmpty(message = "文本key不能为空")
    private String key;

    @ApiModelProperty("文本value")
    @NotEmpty(message = "文本value不能为空")
    private String value;

    @ApiModelProperty("项目id")
    @NotEmpty(message = "项目id不能为空")
    private String projectId;
}
