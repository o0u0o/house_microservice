# 房产销售系统微服务

## 环境
- Java1.8
- SpringBoot 2.2.2.RELEASE
- 


1、先启动8666
mvn spring-boot:run
2、再启动
mvn spring-boot:run -Dspring.profiles.active=peer

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

 
