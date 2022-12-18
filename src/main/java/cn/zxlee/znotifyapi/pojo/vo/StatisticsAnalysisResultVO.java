package cn.zxlee.znotifyapi.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @program: z-notify-api
 * @description: StatisticsDetailResultVO
 * @author: zxlee
 * @create: 2022-12-18 18:38
 **/

@Data
@ApiModel("统计结果详情VO")
public class StatisticsAnalysisResultVO {
    @ApiModelProperty("访问次数")
    private Integer viewCount;
    @ApiModelProperty("访问人数，根据ip区分访问次数，相同ip只算一次")
    private Integer visitorCount;
    @ApiModelProperty("今日总访问次数")
    private Integer todayViewCount;
    @ApiModelProperty("今日总访问人数")
    private Integer todayVisitorCount;
    @ApiModelProperty("昨日总访问次数")
    private Integer yesterdayViewCount;
    @ApiModelProperty("昨日总访问人数")
    private Integer yesterdayVisitorCount;
    @ApiModelProperty("近7天总访问次数")
    private Integer days7ViewCount;
    @ApiModelProperty("近30天总访问次数")
    private Integer days30ViewCount;

    @ApiModelProperty("近10天每天的访问次数")
    private List<StatisticsDateCountVO> days10CountList;
    @ApiModelProperty("近12个月每月的访问次数")
    private List<StatisticsDateCountVO> months12CountList;
    @ApiModelProperty("各个ip归属地数量")
    private List<StatisticsRegionCountVO> ipRegionCountList;
}
