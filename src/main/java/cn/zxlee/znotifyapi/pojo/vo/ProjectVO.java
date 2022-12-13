package cn.zxlee.znotifyapi.pojo.vo;

import cn.zxlee.znotifyapi.pojo.BasePOJO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: z-notify-api
 * @description: 项目VO
 * @author: zxlee
 * @create: 2022-11-27 00:22
 **/

@Data
@ApiModel("项目VO")
public class ProjectVO extends BasePOJO {
    @ApiModelProperty("项目名称")
    private String projectName;
    @ApiModelProperty("用户id")
    private String userId;
    @ApiModelProperty("通知数量")
    private Long noticeCount;
    @ApiModelProperty("text数量")
    private Long textCount;
    @ApiModelProperty("version数量")
    private Long versionCount;
}
