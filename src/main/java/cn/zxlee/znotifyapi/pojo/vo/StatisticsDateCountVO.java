package cn.zxlee.znotifyapi.pojo.vo;

import cn.zxlee.znotifyapi.pojo.BasePOJO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: z-notify-api
 * @description: StatisticsDateCountVO
 * @author: zxlee
 * @create: 2022-12-17 17:05
 **/

@Data
@ApiModel("统计结果数量VO")
public class StatisticsDateCountVO {
    @ApiModelProperty("日期")
    private String date;
    @ApiModelProperty("访问次数")
    private Integer count;
}
