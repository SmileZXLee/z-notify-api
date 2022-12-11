package cn.zxlee.znotifyapi.pojo.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

/**
 * @program: z-notify-api
 * @description: 用户注册BO
 * @author: zxlee
 * @create: 2022-11-29 17:53
 **/
@Data
@ApiModel("用户注册BO")
public class UserRegisterBO {
    @ApiModelProperty(value = "邮箱", required = true)
    @NotEmpty(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    private String account;

    @ApiModelProperty(value = "密码", required = true)
    @NotEmpty(message = "密码不能为空")
    private String password;

    @ApiModelProperty(value = "密码", required = true)
    @NotEmpty(message = "验证码不能为空")
    private String checkCode;
}
