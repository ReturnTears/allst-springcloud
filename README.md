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

## 项目层级及模块介绍
```text
common公共模块
eureka、eureka2是eureka服务注册与发现集群
pay、pay2是服务提供者，都注册进了eureka集群
order是消费者、注册进了eureka集群
zookeeper是zookeeper集群客户端，注册进了zookeeper集群
zkOrder是消费者、注册进了zookeeper集群
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
restTemplate.getForObject:返回对象为响应体中数据转化成的对象，基本上可以理解为JSON
restTemplate.getForEntity返回对象为ResponseEntity对象，包含了响应中的一些重要信息，比如响应头、响应状态码和响应体

JdbcTemplate
RedisTemplate

```

## 服务注册与发现
```text
什么是服务治理？
SpringCloud封装了Netflix公司开发 的Eureka模块来实现服务治理
在传统的RPC远程调用框架中，管理每个服务与服务之间依赖关系比较复杂，管理比较复杂，所以需要服务治理管理服务与服务之间依赖关系。
可以实现<服务调用><负载均衡><容错>等，实现服务发现与注册。

什么是服务注册与发现？
服务注册是指服务提供者向注册中心注册服务实例，主要将其服务信息如服务名称、IP 地址、端口等注册到注册中心。
服务发现是指服务消费者需要调用其他服务时，注册中心将服务提供者的服务信息如服务名称、IP 地址、端口等告知服务消费者。

Eureka包含两个组件: Eureka Server和Eureka Client

Eureka集群
eureka与eureka2相互注册

服务发现Discovery
@EnableDiscoveryClient

Eureka的保护机制
保护模式主要用于一组客户端和Eureka Server之间存在网络分区场景下的保护，一旦进入保护模式，Eureka Server将尝试保护其服务注册表中的信息，
不在删除注册表中的数据，也就是不会注销任何微服务。
为什么会产生Eureka自我保护机制？
为了防止EurekaClient可以正常运行，但是与EurekaServer网络不通的情况下，EurekaServer不会立刻将EurekaServer服务剔除。
什么是自我保护机制？
默认情况下，如果EurekaServer在一定时间内没有接收到某个服务的心跳，EurekaServer将会注销该实例(默认90S)

自我保护机制是一种应对网络故障的措施。

怎么禁止自我保护？

```

## zookeeper
```text
Zookeeper服务注册与发现
环境介绍：Zookeeper(3.4.10)作为虚拟机上运行的集群服务，服务提供者和服务消费者都为Windows10下的本地Maven程序运行的服务。
查看Zookeeper版本: echo stat|nc localhost 2181
zookeeper注册的服务节点是临时的
```

## consul服务注册与发现
```text
is what?
Consul:是一套开源的分布式服务发现和配置管理系统，由HashiCrop公司采用go语言开发
提供了微服务系统中的服务治理、配置中心、控制总线等功能。这些功能中的每一个都可以根据需要单独使用。
也可以一起使用以构建全方位的服务网格，consul提供了整套网格服务。
do what?
服务发现：提供http和DNS两种发现方式。
健康健康：支持多种方式，Http、TCP、Docker、Shell脚本定制化
KV存储：K-V键值对方式存储
多数据中心：支持多数据中心
可视化Web界面
download where?
地址：consul.io/downloads.html
hao to play?
中文翻译：springcloud.cc/spring-cloud-consul.html

官网安装说明：
https://learn.hashicorp.com/consul/getting-started/install.html
下载完成后只有一个consul.exe文件
安装：consul.exe
运行：consul agent -dev
查看版本：consul --version
查看web界面：localhost:8500

```

## 三注册中心的异同点
```text
组件名       语言      CAP     服务健康检查      对外暴露接口      SpringCloud集成   
Eureka      Java      AP      可配支持          Http            已集成
Consul      Go        CP      支持             Http/DNS         已集成
Zookeeper   Java      CP      支持             客户端            已集成
Consistency 强一致性
Availability 可用性
Partition tolerance 分区容错性
一个系统只能保证CAP的两者
```

## Ribbon负载均衡服务调用
```text
is what ?
Spring Cloud Ribbon是基于Netflix实现的一套客户端 负载均衡的工具
简单的说：Ribbon是Netflix发布的开源项目，主要功能是提供客户端的软件负载均衡算法和服务调用。
Ribbon客户端提供了一系列完善的配置，如连接超时、重试、。
简单说就是在配置文件中列出Load Balance后面所有的机器，Ribbon会自动的帮你基于某种规则(轮询、随机)
去连接这些机器。我们就很容易实现自定义的负载均衡算法

LB is what ?
简单说就是将用户的请求平摊的分配到多个服务上，从而达到系统的HA
常见负载均衡有：Nginx、LVS、硬件F5
Ribbon本地负载均衡客户端 VS Nginx服务端负载均衡
Nginx是服务器负载均衡，客户端所有请求都会交给Nginx,然后由nginx实现请求转发，即负载均衡由服务端实现。
Ribbon是本地负载均衡、再调用微服务接口时候，会在注册中心上获取注册信息服务列表之后缓存到本地JVM，从而在本地实现RPC远程服务调用技术。
Nigix是集中式LB
Ribbon是进程内LB

Ribbon ≈ 负载均衡 + restTemplate调用

Ribbon核心组件IRule: 根据特定算法从服务列表中选取一个要访问的服务。
    IRule自带了：轮询、随机...7种算法

如何替换：
    自定义配置类不能放在@ComponentScan所扫描的当前包以及子包下，否则自定义的这个配置类就会被所有的Ribbon客户端所共享，达不到特殊定制化的目的。
    自定义规则包：com.allst.irule

原理>>
负载均衡的算法: rest接口第几次请求数 % 服务器集群总数量 = 实际调用服务器位置下标， 每次服务重启后rest接口计数从1开始
算法>>
手写>>
    手写步骤：
        1、ApplicationContextBean去掉@LoadBalanced
        2、LoadBalance接口
        3、MyLB
        4、OrderController
```

## OpenFeign服务接口调用
```text
概述:
Feign是一个声明式WebService客户端， 使用Feign能让编写WebService客户端更加简单。
它的使用方法是定义一个服务接口然后在上面添加注解。Feign也支持可插拔式的编码器和解码器。
Spring Cloud堆Feign进行了封装，使其支持SpringMVC注解标准和HttpMessageConverters。
Feign可以与Eureka和Ribbon组合使用以支持负载均衡
Github Code: https://github.com/spring-cloud/spring-cloud-openfeign

what to do?
Feign旨在使编写Java Http客户端变得更加容易
使用Ribbon+RestTemplate时， 利用RestTemplate堆http请求的封装，形成了一套模板化的调用方法。
但是在实际开发中，由于对服务依赖的调用可能不止一处，往往一个接口会被多处调用，所以通常会针对每个微服务自行封装一些客户端来包装这些依赖服务的调用。
所以，Feign在此基础上做了进一步封装，由他来帮助我们定义和实现依赖服务接口的定义。
在Feign的实现下，我们只需要常见一个接口并使用注解的方式来配置它，即可完成对服务提供方的接口绑定，简化了使用Spring Cloud Ribbon时，自动封装服务客户端的开发量

Feign集成Ribbon
利用Ribbon维护了服务提供者的服务列表信息，并通过轮询实现了客户端的负载均衡。而Ribbon不同的是，通过feign只需要定义服务绑定接口且以声明式的方法，优雅而简单的实现了服务调用。
Feign集成了Ribbon，自带负载均衡配置项

使用步骤:
allst-cloud-feignOrder
```
