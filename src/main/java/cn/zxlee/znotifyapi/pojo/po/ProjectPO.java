package cn.zxlee.znotifyapi.pojo.po;

import cn.zxlee.znotifyapi.pojo.BasePOJO;
import lombok.Data;

import java.util.List;

/**
 * @program: z-notify-api
 * @description: 项目PO
 * @author: zxlee
 * @create: 2022-11-27 00:22
 **/

@Data
public class ProjectPO extends BasePOJO {
    private String projectName;
    private String userId;
    private Long noticeCount;
    private Long textCount;
    private Long versionCount;
}
