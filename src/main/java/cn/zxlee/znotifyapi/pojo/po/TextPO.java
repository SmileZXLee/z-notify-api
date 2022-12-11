package cn.zxlee.znotifyapi.pojo.po;

import cn.zxlee.znotifyapi.pojo.BasePOJO;
import lombok.Data;

import java.util.Date;

/**
 * @program: z-notify-api
 * @description: 文本PO
 * @author: zxlee
 * @create: 2022-12-07 19:40
 **/

@Data
public class TextPO extends BasePOJO {
    private String title;
    private String key;
    private String value;
    private String projectId;
    private Date expiretime;
    private Short status;
}
