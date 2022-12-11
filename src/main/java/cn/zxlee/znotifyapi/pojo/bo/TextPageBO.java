package cn.zxlee.znotifyapi.pojo.bo;

import cn.zxlee.znotifyapi.pojo.bo.base.PageBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: z-notify-api
 * @description: 文本分页BO
 * @author: zxlee
 * @create: 2022-12-07 19:56
 **/

@Data
@ApiModel("文本分页BO")
public class TextPageBO extends PageBO {
    @ApiModelProperty("文本key关键字")
    private String keyword;
}
