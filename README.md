# 房产销售系统微服务

## 环境
- Java1.8
- SpringBoot 2.2.2.RELEASE
-  

### 项目启动顺序
注册中心使用Eureka
- 1、先启动Eureka 端口 8666
- 2、再启动api-gateway 端口 8080
- 3、在启动其他服务
```bash
mvn spring-boot:run
```


2、再启动
mvn spring-boot:run -Dspring.profiles.active=peer

mvn spring-boot:run -Dspring.profiles.active=usercopy 



## 微服务面临的问题和挑战
### 1、服务通信
服务与服务之间通信
- 通信技术方案： RPC、REST、异步消息

1、RPC：基于tcp通信 
优点：ide 有代码提示 缺点：不支持跨语言

- 服务注册和发现
- 负载均衡

### 2、服务网关
- API Gateway
- 为前端服务的后端(Backends For Forntends)
- 身份认证、路由服务、流量控制、日志通知可放在服务网关，具体业务不应放在服务网关中

### 高可观察
- 健康检查、集中监控
- 日志聚合及检索（可使用ELK）
- 分布式追踪

### 可靠性
- 流量控制，超时控制
- 舱壁隔离，熔断机制
- 服务不可用时：服务降级、幂等重试

## 微服务拆分原则和方法
- 单一职责、高内聚低耦合原则
- 服务粒度适中
- 考虑团队结构

## Spring Cloud Eureka
### Eureka 的高可用
- 创建两个application.properties 配置
- 配置到不同的profile中
- 指定profile参数分别启动

#### Spring Cloud Eureka 的核心特性
- Eureka通过相互注册与复制支持高可用
- Eureka 支持用户认证
- Eureka Client 支持注册表缓存（容错机制）
- Eureka的保护模式（解决网络分区故障）
- 服务提供方上报健康检查信息
- Eureka 注册RESTful API（附Rest端点）

#### Spring Cloud Eureka 的缺点
- 不支持事件（注销、注册、失效）通知

## 服务通信组件 RestTemplate
### Spring RestTemplate 介绍
- 访问Rest服务的客户端组件 


## Spring Cloud Ribbon
- Ribbon介绍：
    - 是Netflix公司发布的客户端负载均衡器
    - 已集成在SpringCloud Neflix 套件中
### 自定义Ribbon
- 创建客户端配置类
- 通过@RibbonClient指定客户端配置类

## 微服务代码脚手架搭建
- 服务注册发现组件 - Eureka
- 负载均衡组件
- 服务通信组件
- 数据访问层代码
- 异常统一处理
- HTTP日志组件，JSON解析器


## Spring cloud Feign
- 介绍

    - 声明式HTTP客户端，方便构建HTTP请求
    - 与Ribbon、Eureka 无缝集成
    - 支持Spring mvc 注解
   
 ## API-Gateway
 
 
 ## 级联故障解决方案
 1、故障隔离
 - 舱壁隔离(线程隔离)
 - 超时控制
 - 服务降级
 - 熔断机制