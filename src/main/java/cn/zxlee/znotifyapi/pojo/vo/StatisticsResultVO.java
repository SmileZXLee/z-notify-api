package cn.zxlee.znotifyapi.pojo.vo;

import cn.zxlee.znotifyapi.pojo.BasePOJO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: z-notify-api
 * @description: StatisticsBO
 * @author: zxlee
 * @create: 2022-12-17 17:05
 **/

@Data
@ApiModel("统计结果VO")
public class StatisticsResultVO {
    @ApiModelProperty("访问次数")
    private Integer viewCount;
    @ApiModelProperty("根据ip区分访问次数，相同ip只算一次")
    private Integer visitorCount;
}
