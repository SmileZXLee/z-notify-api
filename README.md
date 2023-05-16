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
* 版本管理，通过公共接口返回所有新版本号、更新内容、下载地址

* 通知管理，支持发布通知和设置过期时间，通过公共接口获取所有未过期的通知

* 通用文本管理，可以随意自定义key并通过公共接口获取key对应的文本

* 反馈管理，提供用户反馈提交页，用户提交后可在管理后台查看并回复，用户可在反馈页查看开发者回复的内容

* 用户流量统计&分析，支持生成`badge`嵌入网页统计访问次数，支持根据ip统计访问人数等，流量分析可在管理后台查看，例如：在网页或md中插入`![visitors](https://api.z-notify.zxlee.cn/v1/public/statistics/8299976976587751424/badge)`

## 预览
#### 项目列表
[![overview](https://admin.z-notify.zxlee.cn/public/overview.png)](https://admin.z-notify.zxlee.cn)
#### 统计分析
[![overview](https://admin.z-notify.zxlee.cn/public/demo-analysis-2.png)](https://admin.z-notify.zxlee.cn)

## 使用到的技术
* spring-boot+druid+mybatis实现接口处理和数据库访问，数据库使用MySQL
* 接口风格遵循RESTful Api规范；接口文档使用swagger3.0生成
* 使用spring-boot-validation进行统一参数校验
* 全局异常拦截&处理；统一接口返回格式
* 基础的Mapper、Service、分页等封装；id通过雪花算法生成
* 使用redis进行token的存储&统一身份认证
* 使用redis进行邮箱验证码存储；使用spring-boot-mail+thymeleaf发送指定样式的邮箱验证码
* 多文件上传至oss
