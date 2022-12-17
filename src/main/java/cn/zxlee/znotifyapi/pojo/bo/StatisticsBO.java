package cn.zxlee.znotifyapi.pojo.bo;

import cn.zxlee.znotifyapi.pojo.BasePOJO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @program: z-notify-api
 * @description: StatisticsBO
 * @author: zxlee
 * @create: 2022-12-17 17:05
 **/

@Data
@ApiModel("统计BO")
public class StatisticsBO extends BasePOJO {
    @ApiModelProperty("项目id")
    @NotEmpty(message = "项目id不能为空")
    private String projectId;
    @ApiModelProperty("访问者的ip地址")
    private String ip;
    @ApiModelProperty("访问者的ip归属地")
    private String ipRegion;
}
