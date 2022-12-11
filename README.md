# z-notify-api
<p align="center">
    <img alt="logo" src="https://admin.z-notify.zxlee.cn/logo.png" width="120" height="120" style="margin-bottom: 10px;">
</p>
<h3 align="center" style="margin-top: 30px;font-size:40px;">Z-Notify-Api</h3>
<h5 align="center">应用统一在线管理平台</h5>

## 常用链接

### 后台管理页面地址
[https://admin.z-notify.zxlee.cn](https://admin.z-notify.zxlee.cn)

### API文档地址
[https://api.z-notify.zxlee.cn/swagger-ui/index.html](https://api.z-notify.zxlee.cn/swagger-ui/index.html)

### API接口地址
[https://api.z-notify.zxlee.cn](https://api.z-notify.zxlee.cn)

## 使用到的技术
* spring-boot+druid+mybatis实现接口处理和数据库访问，数据库使用MySQL
* 接口风格遵循RESTful Api规范；接口文档使用swagger3.0生成
* 使用spring-boot-validation进行统一参数校验
* 全局异常拦截&处理；统一接口返回格式
* 基础的Mapper、Service、分页等封装；id通过雪花算法生成
* 使用redis进行token的存储&统一身份认证
* 使用redis进行邮箱验证码存储；使用spring-boot-mail+thymeleaf发送指定样式的邮箱验证码

## 其他功能
正在开发(有空慢慢完善)
