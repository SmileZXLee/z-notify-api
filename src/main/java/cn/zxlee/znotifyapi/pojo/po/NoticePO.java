package cn.zxlee.znotifyapi.pojo.po;

import cn.zxlee.znotifyapi.pojo.BasePOJO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @program: z-notify-api
 * @description: 通知PO
 * @author: zxlee
 * @create: 2022-11-25 22:03
 **/

@Data
public class NoticePO extends BasePOJO {
    private String title;
    private String content;
    private String projectId;
    private Date expiretime;
    private Short status;
}
