package cn.zxlee.znotifyapi.pojo.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * @program: z-notify-api
 * @description: UserBO
 * @author: zxlee
 * @create: 2022-11-26 00:51
 **/

@Data
@ApiModel("UserBO")
public class UserBO {
    @ApiModelProperty(value = "邮箱", required = true)
    @NotEmpty(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    private String account;

    @ApiModelProperty(value = "密码", required = true)
    @NotEmpty(message = "密码不能为空")
    private String password;
}
