package cn.zxlee.znotifyapi.pojo.po;

import cn.zxlee.znotifyapi.pojo.BasePOJO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: z-notify-api
 * @description: StatisticsPO
 * @author: zxlee
 * @create: 2022-12-17 17:05
 **/

@Data
public class StatisticsPO extends BasePOJO {
    private String projectId;
    private String ip;
    private String ipRegion;
    private String tag;
    private String from;
}
