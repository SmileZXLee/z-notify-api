package cn.zxlee.znotifyapi.pojo.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @program: z-notify-api
 * @description: 项目BO
 * @author: zxlee
 * @create: 2022-11-27 00:22
 **/

@Data
@ApiModel("项目BO")
public class ProjectBO {
    @ApiModelProperty("项目名称")
    @NotEmpty(message = "项目名称不能为空")
    private String projectName;
}
