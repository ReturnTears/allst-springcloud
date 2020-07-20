# 微服务架构搭建
## 环境介绍
```text
Windows10专业版、IDEA2020.1、Maven3.6.3、Git、MySQL8
如果IDEA新建Maven项目时报错：请查看我的博客：
https://blog.csdn.net/sinat_33957978/article/details/107453167
```
## 技术栈
```text
Springboot 、 SpringCloud全家桶
```

## 流程
```text
约定 > 配置 > 编码
父工程: 
微服务模块: 建module、改pom、写yml或者properties、主启动类、业务类
    业务类: 建库/建表、entities、dao、service、controller

添加Devtools热部署插件
1、添加依赖坐标到pom.xml文件中
2、添加插件配置
3、Settings > Build Execution Deployment > Compiler > A/B/C/D开头的选项都需要勾选上
4、 Ctrl + Shift + alt + / > Registry > compiler.automake.allow.when.app.running 勾选上、actionSystem.assertFocusAccessFromEdt勾选上
5、重启IDEA（2020+版本忽略此步骤也可以）

RestTemplate提供了多种边界访问远程Http服务的方法，是一种简单便捷的访问restful服务模板类，是Spring提供的用于访问Rest服务的客户端模板工具集
JdbcTemplate
RedisTemplate

```