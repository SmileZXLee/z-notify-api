package cn.zxlee.znotifyapi.pojo.bo;

import cn.zxlee.znotifyapi.annotation.enumValidator.EnumValidator;
import cn.zxlee.znotifyapi.enums.StatisticsVisitorBy;
import cn.zxlee.znotifyapi.pojo.bo.base.PageBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: z-notify-api
 * @description: 统计分析分页BO
 * @author: zxlee
 * @create: 2022-11-27 00:22
 **/

@Data
@ApiModel("统计分析分页BO")
public class StatisticsPageBO extends PageBO {
    @ApiModelProperty("ip搜索关键字")
    private String keyword;
}
