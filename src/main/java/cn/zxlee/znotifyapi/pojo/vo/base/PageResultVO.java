package cn.zxlee.znotifyapi.pojo.vo.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @program: z-notify-api
 * @description: 分页VO
 * @author: zxlee
 * @create: 2022-11-29 10:39
 **/

@Data
@ApiModel("分页结果")
public class PageResultVO<T> {
    @ApiModelProperty("总条数")
    private Long total;

    @ApiModelProperty("当前页数")
    private int current;

    @ApiModelProperty("每页条数")
    private int pageSize;

    @ApiModelProperty("结果集合")
    private List<T> results;
}
