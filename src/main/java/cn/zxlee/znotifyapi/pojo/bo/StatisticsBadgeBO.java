package cn.zxlee.znotifyapi.pojo.bo;

import cn.zxlee.znotifyapi.annotation.enumValidator.EnumValidator;
import cn.zxlee.znotifyapi.enums.StatisticsBadgeType;
import cn.zxlee.znotifyapi.pojo.BasePOJO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @program: z-notify-api
 * @description: StatisticsBadgeBO
 * @author: zxlee
 * @create: 2022-12-17 17:05
 **/

@Data
@ApiModel("统计BO")
public class StatisticsBadgeBO {
    @ApiModelProperty("获取badge中值的类型，有view_count(访问次数)和visitor_count(访问人数，根据ip区分)两种，默认为view_count")
    @EnumValidator(value = StatisticsBadgeType.class)
    private String type;
    @ApiModelProperty("badge左边的标题，若不填则默认为type的枚举")
    private String title;
    @ApiModelProperty("badge的颜色")
    private String color;
    @ApiModelProperty("badge的样式，可选值为plastic、flat、flat-square、for-the-badge、social")
    private String style;

    public String getType() {
        return null == type ? StatisticsBadgeType.VIEW_COUNT.getValue() : type;
    }

    public String getTitle() {
        return null == title ? this.getType().toString() : title;
    }

    public String getColor() {
        return null == color ? "blue" : color;
    }

    public String getStyle() {
        return null == style ? "plastic" : style;
    }
}
