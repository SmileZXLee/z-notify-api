package cn.zxlee.znotifyapi.pojo.po;

import cn.zxlee.znotifyapi.pojo.BasePOJO;
import lombok.Data;

import java.util.List;

/**
 * @program: z-notify-api
 * @description: FeedbackPO
 * @author: zxlee
 * @create: 2022-12-13 19:18
 **/

@Data
public class FeedbackPO extends BasePOJO {
    private String projectId;
    private String username;
    private String contact;
    private String content;
    private String extraInfo;
    private List imgUrls;
}
