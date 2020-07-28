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

超时控制:
默认Feign客户端只等待一秒钟，但是服务端处理需要超过1秒，导致feign客户端不想等待了，直接返回报错，
为了避免这样的情况，有时候我们需要设置feign客户端的超时控制。

日志打印功能
Feign提供了日志打印功能，我们可以通过配置来调整日志级别，从而了解feign中http请求细节，即对feign接口的调用情况进行了监控和输出
日志级别:
    NONE: 默认的， 不显示任何日志
    BASIC: 仅记录请求方法、URL、响应状态码即执行时间
    HRADERS: 除了BASIC中定义的信息之外，还有请求和响应的头信息
    FULL: 除了HEADERS中定义的信息之外， 还有请求和响应的正文及元数据

```
## Hystrix断路器
```text
多个服务之间调用的时候， 假设微服务A调用服务B和微服务C,微服务B和微服务C又调用其他的微服务，这种调用关系称为“扇出”
如果扇出的链路上某个微服务的调用响应时间过长或者不可用，对微服务A的调用就会占用越来越多的系统资源，进而引起系统崩溃，这种现象就是“雪崩效应”
就是服务的高可用受到破坏

Hystrix 概述:
Hystrix is what?
Hystrix 是一个用于处理分布式系统的‘延迟’和‘容错’的开源库。在分布式系统里，许多依赖不可避免的会调用失败，比如：超时，异常等。
Hystrix能够保证在一个依赖出问题的情况下，不会导致整体服务失败，避免级联故障，以提高分布式系统的弹性。
“熔断器”本身是一种开关装置，当某个服务单元发生故障之后，通过断路器的故障监控（类似熔断保险丝），向调用方返回一个符合
预期的，可处理的备选响应，而不是长时间的等待或者抛出调用方无法处理的异常，这样就保证了服务调用方的线程不会被长时间、不必要的占用，从
而避免了故障在分布式系统中的蔓延，乃至雪崩

Hystrix can do?
github.com/Netflix/Hystrix/wiki/How-To-Use

Hystrix 重要概述:
服务降级(fallback):
    服务器忙，请稍后再试，不让客户端等待并立即返回一个友好的提示，fallback
    那些情况会发生降级：程序运行异常，超时，服务熔断触发服务降级，线程池/信号量打满也会导致服务降级
    那些情况会触发降级？
    1、程序运行异常
    2、超时
    3、服务熔断触发服务降级
    4、线程池/信号量打满也会导致服务降级

服务熔断(break)：
    类比保险丝达到最大服务访问后，直接拒绝访问，拉闸限电，然后调用服务降级的方法友好返回提示。
    就是保险丝 服务的降级 》 进而熔断 》 恢复调用链路
    断路器: 可以理解为家用电路的保险丝
  熔断是什么?
    熔断是机制是应对雪崩效应的一种微服务链路保护机制。当扇出链路的某个微服务出错不可用或者响应时间太长时，
    会进行服务的降级，进而熔断该节点微服务的调用，快速返回错误的响应信息。
    当检测到该节点微服务调用响应正常后，恢复调用链路。
    在SpringCloud框架里，熔断机制通过Hystrix实现，Hystrix会监控微服务间调用的状况，当失败的调用到一定域值，
    缺省是5秒内20次调用失败，就会启动熔断机制。熔断机制的注解@HystrixCommand
  总结：
    熔断类型:
    open - half open - closed
    熔断打开: 请求不再进行调用当前服务，内部设置时钟一般为MTTR(平均故障处理时间)，当打开时长达到所设时钟则进入半熔断状态
    熔断半开：部分请求根据规则调用当前服务，如果请求成功且符合规则则认为当前服务恢复正常，关闭熔断
    熔断关闭: 熔断关闭不会对服务进行熔断（相当于保险丝是关闭状态）
  importance:
    涉及到断路器的三个重要参数: 快照时间窗口、请求总数阈值、错误百分比阈值
    快照时间窗口: 断路器确定是否打开需要统计一些请求和错误数据，而统计的时间范围就是快照时间窗口，默认为最近的10秒
    请求总数阈值: 在快照时间窗内，必须满足请求总数阈值才有资格熔断，默认为20，意味着在9秒内，如果还Hystrixmmin命令的调用次数不超过20次，
                即使所有的请求都超时或者其他原因失败，断路器都不会打开。
    错误百分比阈值: 当请求总数在快照时间窗口内超过了阈值，比如：发生了30次调用，如果在每次调用，有15次发生了异常，也就是超时50%的错误百分比，
                在默认设定50%阈值情况下，这时候就会将断路器打开。
  断路器打开后，再有请求调用时，将不会调用主逻辑，而是直接调用降级fallback,通过断路器，实现了自发的发现错误并进行降级逻辑切换为主逻辑，减少响应延迟的效果。
  原来的主逻辑要如何恢复呢？
  hystrix也为我们实现了自动恢复功能
  当断路器打开，对主逻辑进行熔断之后，hystrix会启动一个休眠时间窗，在这个事件窗内，降级逻辑时临时的称为主逻辑，
    当休眠时间窗到期，断路器将进入半开状态，释放一次请求到原来的主逻辑上，如果再次请求正常返回，那么断路器将继续闭合，
    主逻辑恢复，如果这次请求依然有问题，断路器继续进入打开状态，休眠时间窗重新计时。
服务限流(flowlimit):
    秒杀高并发等操作，一秒N个请求，有序进行
    跳转到Alibaba的sentinel
基于allst-cloud-hystrix测试断路器
Hystrix还提供了准实时的调用监控（Hystrix Dashboard）,Hystrix会持续地记录所有通过Hystrix发起的请求的执行信息。
并以统计报表和图标的形式展示给用户，包括每秒执行多少请求多少成功，多少失败等， Netflix通过Hystrix-metrics-event-stream项目
实现了对以上指标的监控。Spring Cloud也提供了Hystrix Dash Board的整合，对监控内容转化成可视化界面。
Success | Short-Circuited | Bad Request | Timeout | Rejected | Failure | Error %
7色 
http://localhost:9001/hystrix
http://localhost:8001/actuator/hystrix.stream

```

## 服务网关
```text
Zuul
https://github.com/Netflix/zuul/wiki

Gateway(新一代服务网关)
https://cloud.spring.io/spring-cloud-static/spring-cloud-gateway/2.2.2.RELEASE/reference/html
概述:
is what?
cloud全家桶中有个很重要的组件就是网关，在1.x版本中都是采用的Zuul网关
在2.x中采用的SpringCloud Gateway
Gateway是在Spring生态系统之上构建的API网关服务，基于Spring 5、Spring Boot 2、和Project Reactor等技术。
Gateway旨在提供一种简单有效的方式来对API进行路由， 以及提供一些强大的过滤功能，例如：熔断，限流，重试
SpringCloud Gateway是基于WebFlux实现的，而WebFlux框架底层则是使用了高性能的Reactor通信框架Netty
SpringCloud Gateway的目标提供统一的路由方式且基于Filter链的方式提供了网关基本功能

SpringCloud Gateway具有以下特性:
基于Spring Framework 5, Project Reactor和Spring Boot2.0
动态路由:能够匹配任何请求属性
可以对路由指定Predicate（断言）和Filter（过滤器）
集成Hystrix的断路器功能
集成Spring Cloud服务发现功能
易于编写的Predicate（断言）和Filter（过滤器）
请求限流功能
支持路径重写

what can do?
反向代理
鉴权
流量控制
熔断
日志监控
...

三大核心概念:
路由（路由）:
    路由是构建网关的基本模块，它由ID，目标URI，一系列的断言和过滤器组成，如果断言为true则匹配该路由
Predicate（断言）:
    参考Java8 的java.util.function.Predicate
    开发中可以匹配Http请求中的所有内容(例如请求头或请求参数)， 如果请求与断言相匹配则进行路由
Filter（过滤）:
    值得是Spring框架中SpringCloud Gateway Filter的实例，使用过滤器，可以在请求被路由前或者之后进行修改。

Gateway工作流程
    客户端向SpringCloud Gateway 发出请求。然后在Gateway Handler Mapping中找到与请求相匹配的路由，将其发送到Gateway Web Handler。
    Handler在通过指定的过滤器链Filter来将请求发送到我们实际的服务执行业务逻辑，然后返回。
    过滤器之间用虚线分开是因为过滤器可能会在发送代理之前("pre")或之后"post"执行业务逻辑
        <pre:可以做参数校验、权限校验、流量监控、日志输出、协议转换|post:响应内容、响应头的修改、日志输出、流量监控>

入门配置
Gateway也是一个微服务，需要注册进微服务


通过微服务服务名称实现动态路由
默认情况下Gateway会根据注册中心注册的服务列表，以注册中心上微服务名为路径常见动态路由转发，从而实现动态路由的功能



```