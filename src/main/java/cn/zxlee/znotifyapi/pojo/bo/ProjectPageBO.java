package cn.zxlee.znotifyapi.pojo.bo;

import cn.zxlee.znotifyapi.pojo.bo.base.PageBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: z-notify-api
 * @description: 项目分页BO
 * @author: zxlee
 * @create: 2022-11-27 00:22
 **/

@Data
@ApiModel("项目分页BO")
public class ProjectPageBO extends PageBO {
    @ApiModelProperty("项目名称搜索关键字")
    private String keyword;
}
