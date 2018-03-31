# 项目说明
- dubbo-api : api接口
- dubbo-consumer: 服务消费者
- dubbo-provider: 服务提供者
consumer可通过接口调用provider提供的服务 。同时consumer本身也是服务提供者，服务内部也可以通过dubbo协议调用

# 添加依赖
```xml
<dependency>
    <groupId>com.alibaba.boot</groupId>
    <artifactId>dubbo-spring-boot-starter</artifactId>
    <version>${springboot.dubbo}</version>
</dependency>
<!-- Dubbo -->
<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>dubbo</artifactId>
    <version>${dubbo.version}</version>
    <exclusions>
        <exclusion>
            <groupId>org.springframework</groupId>
            <artifactId>spring</artifactId>
        </exclusion>
        <exclusion>
            <groupId>javax.servlet</groupId>
            <artifactId>servlet-api</artifactId>
        </exclusion>
    </exclusions>
</dependency>
<!-- ZK -->
<dependency>
    <groupId>org.apache.zookeeper</groupId>
    <artifactId>zookeeper</artifactId>
    <version>${zookeeper.version}</version>
    <exclusions>
        <exclusion>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-log4j12</artifactId>
        </exclusion>
    </exclusions>
</dependency>
<dependency>
    <groupId>com.101tec</groupId>
    <artifactId>zkclient</artifactId>
    <version>${zkclient.version}</version>
    <exclusions>
        <exclusion>
            <artifactId>slf4j-api</artifactId>
            <groupId>org.slf4j</groupId>
        </exclusion>
        <exclusion>
            <artifactId>log4j</artifactId>
            <groupId>log4j</groupId>
        </exclusion>
        <exclusion>
            <artifactId>slf4j-log4j12</artifactId>
            <groupId>org.slf4j</groupId>
        </exclusion>
    </exclusions>
</dependency>
```
# dubbo-provider
实现demoService接口。通过@Service(version = "1.0.0",group = "demo-group")注解将服务暴露，group指定分组
```java
@Service(version = "1.0.0",group = "demo-group")
public class DemoServiceImpl implements DemoService {
    @Override
    public String sayHello(String name) {
        return "Hello " + name;
    }
}
```
application中配置dubbo相关参数
```properties
dubbo.application.name=dubbo-provider
dubbo.registry.protocol=zookeeper
dubbo.registry.address=zookeeper://127.0.0.1:2181
dubbo.registry.group=mi-pos
dubbo.protocol.name=dubbo
dubbo.protocol.port=20880
dubbo.scan.base-packages=com.ttt.dubbo.provider.service
```
dubbo.scan.base-packages指定dubbo服务扫描包
dubbo.registry.address指定注册中心地址
# dubbo-consumer
通过@Reference(version = "1.0.0",group = "demo-group")注解调用dubbo服务
```java
@Service
public class DemoCousumerService {
    @Reference(version = "1.0.0",group = "demo-group")
    private DemoService demoService;

    public String sayHello(String name) {
        return demoService.sayHello(name);
    }
}
```
注意group应与提供者的group一致，表明是调用哪个分组下的服务。

除了调用其他服务外，dubbo-consumer自己也可以作为服务提供者注册到zk上，并且内部也可以通过dubbo协议调用
提供者实现接口，通过@Service(version = "1.0.0",group = "user-group")暴露服务，同时通过group指明分组
```java
@Service(version = "1.0.0",group = "user-group")
public class UserProviderImpl implements UserApi {
    @Override
    public String getUserName(int id) {
        return "ttt";
    }
}
```
DemoCousumerService中调用该服务,注意分组的指定 
```java
@Reference(version = "1.0.0",group = "user-group")
private UserApi userApi;

public String getUserName(int id) {
    return userApi.getUserName(id);
}
```
application中配置dubbo相关参数 
```java
dubbo.application.name= dubbo-consumer
dubbo.registry.protocol=zookeeper
dubbo.registry.address=zookeeper://127.0.0.1:2181
dubbo.registry.group=mi-pos
dubbo.protocol.name=dubbo
dubbo.protocol.port=20880
dubbo.scan.base-packages=com.ttt.dubbo.consumer.provider
dubbo.consumer.check=false
```
注意需要设置dubbo.consumer.check=false，默认为true,即服务启动时查找dubbo服务提供者是否存在，如果不存在，会抛异常，影响spring的初始化。
由于dubbo-consumer即是提供者也是消费者，此时需要设置其为false.
如果不想全部设置，也可以在@Reference注解中指定check的值 。