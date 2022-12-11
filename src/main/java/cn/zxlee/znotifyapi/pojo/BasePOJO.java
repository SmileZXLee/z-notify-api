package cn.zxlee.znotifyapi.pojo;

import cn.zxlee.znotifyapi.utils.SnowFlake;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * @program: z-notify-api
 * @description:
 * @author: zxlee
 * @create: 2022-11-26 00:42
 **/

@Data
public class BasePOJO {
    @ApiModelProperty("id")
    private String id;
    @ApiModelProperty("创建时间")
    private Date createtime;
    @ApiModelProperty("更新时间")
    private Date updatetime;

    public String getId(){
        if (!StringUtils.hasText(this.id)) {
            this.id = SnowFlake.nextId();
        }
        return this.id;
    }
}
