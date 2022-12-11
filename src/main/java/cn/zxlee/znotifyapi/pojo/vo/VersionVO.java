package cn.zxlee.znotifyapi.pojo.vo;

import cn.zxlee.znotifyapi.pojo.BasePOJO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: z-notify-api
 * @description: 版本VO
 * @author: zxlee
 * @create: 2022-12-11 15:59
 **/

@Data
@ApiModel("版本VO")
public class VersionVO extends BasePOJO {
    @ApiModelProperty("版本")
    private String version;
    @ApiModelProperty("更新内容")
    private String content;
    @ApiModelProperty("下载地址")
    private String downloadUrl;
}
