package cn.zxlee.znotifyapi.pojo.vo;

import cn.zxlee.znotifyapi.pojo.BasePOJO;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

/**
 * @program: z-notify-api
 * @description: 通知VO
 * @author: zxlee
 * @create: 2022-11-25 21:47
 **/

@Data
@ApiModel("通知VO")
public class NoticeVO extends BasePOJO {
    @ApiModelProperty("通知标题")
    private String title;
    @ApiModelProperty("通知内容")
    private String content;
    @ApiModelProperty("项目id")
    private String projectId;
    @ApiModelProperty("过期时间")
    private Date expiretime;
    @ApiModelProperty("通知状态，0-已过期；1-有效")
    private Short status;
}
