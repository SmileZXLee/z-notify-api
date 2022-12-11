package cn.zxlee.znotifyapi.pojo.po;

import cn.zxlee.znotifyapi.pojo.BasePOJO;
import lombok.Data;

/**
 * @program: z-notify-api
 * @description: VersionPO
 * @author: zxlee
 * @create: 2022-12-11 15:48
 **/

@Data
public class VersionPO extends BasePOJO {
    private String projectId;
    private String version;
    private String content;
    private String downloadUrl;
}
