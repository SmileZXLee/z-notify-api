package cn.zxlee.znotifyapi.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: z-notify-api
 * @description: StatisticsTimeCountVO
 * @author: zxlee
 * @create: 2022-12-21 20:34
 **/

@Data
@ApiModel("统计结果数量VO")
public class StatisticsTimeCountVO {
    @ApiModelProperty("小时")
    private Integer hour;
    @ApiModelProperty("访问次数")
    private Integer count;
}
