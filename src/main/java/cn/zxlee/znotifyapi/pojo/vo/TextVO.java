package cn.zxlee.znotifyapi.pojo.vo;

import cn.zxlee.znotifyapi.pojo.BasePOJO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

/**
 * @program: z-notify-api
 * @description: 文本VO
 * @author: zxlee
 * @create: 2022-12-07 19:40
 **/

@Data
@ApiModel("文本VO")
public class TextVO extends BasePOJO {
    @ApiModelProperty("文本key")
    private String key;
    @ApiModelProperty("文本value")
    private String value;
    @ApiModelProperty("项目id")
    private String projectId;
}
