package cn.zxlee.znotifyapi.pojo.vo;

import cn.zxlee.znotifyapi.pojo.BasePOJO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * @program: z-notify-api
 * @description: UserVO
 * @author: zxlee
 * @create: 2022-11-26 01:02
 **/

@Data
@ApiModel("用户信息")
public class UserVO extends BasePOJO {
    private String account;
    private String password;
    private String token;
}
