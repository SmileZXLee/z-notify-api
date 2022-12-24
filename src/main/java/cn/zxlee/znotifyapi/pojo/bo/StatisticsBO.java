package cn.zxlee.znotifyapi.pojo.bo;

import cn.zxlee.znotifyapi.pojo.BasePOJO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.dreamlu.mica.ip2region.core.Ip2regionSearcher;
import org.springframework.beans.factory.annotation.Autowired;

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
    @Autowired
    private Ip2regionSearcher ip2regionSearcher;

    @ApiModelProperty("项目id")
    @NotEmpty(message = "项目id不能为空")
    private String projectId;
    @ApiModelProperty("访问者的ip地址")
    private String ip;
    @ApiModelProperty("访问者的ip归属地")
    private String ipRegion;
    @ApiModelProperty("用于额外区分不同个体的标签")
    private String tag;
}
