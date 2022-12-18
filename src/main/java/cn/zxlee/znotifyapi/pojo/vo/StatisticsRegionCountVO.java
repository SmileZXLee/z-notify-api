package cn.zxlee.znotifyapi.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: z-notify-api
 * @description: StatisticsRegionCountVO
 * @author: zxlee
 * @create: 2022-12-17 17:05
 **/

@Data
@ApiModel("统计结果ip归属地VO")
public class StatisticsRegionCountVO {
    @ApiModelProperty("ip归属地")
    private String region;
    @ApiModelProperty("访问次数")
    private Integer count;
}
