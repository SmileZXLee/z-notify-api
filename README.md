# z-notify-api
<p align="center">
    <img alt="logo" src="https://admin.z-notify.zxlee.cn/logo.png" width="120" height="120" style="margin-bottom: 50px;">
</p>
<h1 align="center">Z-Notify-Api</h1>
<h5 align="center">一个开源的应用统一在线管理平台(api)</h5>

## 链接

* 后台管理页面地址：[https://admin.z-notify.zxlee.cn](https://admin.z-notify.zxlee.cn)

* 后台管理源码地址：[https://github.com/SmileZXLee/z-notify-admin-vue](https://github.com/SmileZXLee/z-notify-admin-vue)

* 公共页(反馈页)源码地址：[https://github.com/SmileZXLee/z-notify-public](https://github.com/SmileZXLee/z-notify-public)

* API文档地址：[https://api.z-notify.zxlee.cn/swagger-ui/index.html](https://api.z-notify.zxlee.cn/swagger-ui/index.html)


## 主要功能
- [x] 版本管理

- [x] 通知管理

- [x] 通用文本管理

- [x] 反馈管理，提供提交反馈页

- [x] 用户流量统计&分析，示例：[![statistics](https://api.z-notify.zxlee.cn/v1/public/statistics/8292724618483712000/badge?color=1890ff&style=flat&title=页面访问统计示例)](https://api.z-notify.zxlee.cn/swagger-ui/index.html#/公共接口/visitAndGetStatisticsOnBadgeUsingGET)，流量分析可在管理后台查看


## 使用到的技术
* spring-boot+druid+mybatis实现接口处理和数据库访问，数据库使用MySQL
* 接口风格遵循RESTful Api规范；接口文档使用swagger3.0生成
* 使用spring-boot-validation进行统一参数校验
* 全局异常拦截&处理；统一接口返回格式
* 基础的Mapper、Service、分页等封装；id通过雪花算法生成
* 使用redis进行token的存储&统一身份认证
* 使用redis进行邮箱验证码存储；使用spring-boot-mail+thymeleaf发送指定样式的邮箱验证码
* 多文件上传至oss

## 其他功能
正在开发...
