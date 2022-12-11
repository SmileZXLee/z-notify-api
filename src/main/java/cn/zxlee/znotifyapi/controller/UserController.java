package cn.zxlee.znotifyapi.controller;

import cn.zxlee.znotifyapi.annotation.NoLoginAuth;
import cn.zxlee.znotifyapi.pojo.bo.UserBO;
import cn.zxlee.znotifyapi.pojo.bo.UserRegisterBO;
import cn.zxlee.znotifyapi.pojo.vo.UserVO;
import cn.zxlee.znotifyapi.response.Result;
import cn.zxlee.znotifyapi.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

/**
 * @program: z-notify-api
 * @description: 用户控制器
 * @author: zxlee
 * @create: 2022-11-26 00:47
 **/

@Slf4j
@RestController
@RequestMapping("/v1/user")
@Api(value = "登录注册", tags = {"登录注册"})
@Validated
public class UserController {
    @Autowired
    private IUserService userService;

    @GetMapping("/getCheckCode")
    @ApiOperation("获取邮箱验证码")
    @NoLoginAuth
    public Result sendCheckCode(@NotEmpty(message = "邮箱不能为空") @Email(message = "邮箱格式不正确") String mail){
        boolean success =  userService.sendCheckCode(mail);
        return success ? Result.success() : Result.fail("验证码发送失败");
    }

    @PostMapping("/register")
    @ApiOperation("用户注册")
    @NoLoginAuth
    public Result doRegister(@Validated @RequestBody UserRegisterBO userBO){
        userService.doRegister(userBO);
        return Result.success();
    }

    @PostMapping("/login")
    @ApiOperation("用户登录")
    @NoLoginAuth
    public Result<UserVO> doLogin(@Validated @RequestBody UserBO userBO){
        return Result.success(userService.doLogin(userBO));
    }

    @PostMapping("/logout")
    @ApiOperation("退出登录")
    @NoLoginAuth
    public Result doLogout(@RequestHeader String token){
        boolean success = userService.doLogout(token);
        return success ? Result.success() : Result.fail("退出登录失败");
    }
}
