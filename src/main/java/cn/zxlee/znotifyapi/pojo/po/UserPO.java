package cn.zxlee.znotifyapi.pojo.po;

import cn.zxlee.znotifyapi.pojo.BasePOJO;
import lombok.Data;

import java.util.Date;

/**
 * @program: z-notify-api
 * @description: UserPO
 * @author: zxlee
 * @create: 2022-11-26 00:41
 **/

@Data
public class UserPO extends BasePOJO {
    private String account;
    private String password;
}
