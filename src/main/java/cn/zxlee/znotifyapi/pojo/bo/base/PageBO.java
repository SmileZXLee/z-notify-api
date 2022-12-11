package cn.zxlee.znotifyapi.pojo.bo.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @program: z-notify-api
 * @description: 分页BO
 * @author: zxlee
 * @create: 2022-11-29 10:39
 **/

@Data
@ApiModel("分页BO")
public class PageBO {
    @ApiModelProperty("页数，从1开始")
    @NotNull(message = "页数不能为空")
    @Min(value = 1, message = "页数不能小于1")
    private Integer current;

    @ApiModelProperty("每页条数")
    @NotNull(message = "每页条数不能为空")
    @Min(value = 1, message = "每页条数不能小于1")
    private Integer pageSize;
}
