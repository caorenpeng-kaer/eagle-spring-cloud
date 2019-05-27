### 基于Spring cloud的服务链路监控"鹰眼计划 V1.0.1" 
* V1.0.1
* 目前依赖的maven 有：amqp,netflix-zuul,openfeign,fastjson

### quick start
* 1.引入SDK
* 2.目前包名必须为五层 cn.com.kaer.*.controller
* 3.路由服务里 需要注入AbstractPostFilter 和AbstractPreFilter
* 4.需要加入 @EnableAsync注解
* 5.配置所需参数
### 配置文件如下(按照自己实际情况配置)
spring.rabbitmq.username=guest
spring.rabbitmq.password=guest
spring.rabbitmq.queue=eagle
spring.rabbitmq.port=5672
spring.rabbitmq.host=127.0.0.1