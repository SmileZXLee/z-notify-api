package cn.zxlee.znotifyapi.pojo.vo.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: z-notify-api
 * @description: 下拉框VO
 * @author: zxlee
 * @create: 2022-12-20 14:44
 **/

@Data
@ApiModel("下拉框VO")
public class SelectVO {
    @ApiModelProperty("label")
    private String label;

    @ApiModelProperty("value")
    private String value;
}
