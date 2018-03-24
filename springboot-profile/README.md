# springboot-profile
- 支持配置文件按业务区分
- 支持从其他jar中加载配置文件
- 支持部署环境区分
- 支持国别

# 说明
## profile-other-jar
打成正常jar包，不要打包成可执行jar包。供profile-web依赖
resources目录下建该业务相关的配置文件，以application开头，通过部署环境区分，部署环境以-区分 ，业务以下划线_区分

建config文件，通过@Value("${other.id}")读取配置文件中的内容。

## profile-web
启动类。打成可执行jar包。
### pom.xml
添加build，打成可执行jar包
```xml
<build>
    <plugins>
        <!-- Package as an executable jar/war -->
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
        </plugin>
    </plugins>
</build>
```
增加对普通jar的依赖
```xml
<dependencies>
    <dependency>
        <groupId>com.ttt.demo</groupId>
        <artifactId>profile-other-jar</artifactId>
    </dependency>
</dependencies>
```
### resources目录下增加配置文件
application.properties为主配置文件，boot加载时首先会读取该配置文件
```properties
server.port=8082
spring.profiles.active=@profileActive@
spring.profiles.region=@profileRegion@
spring.profiles.include=@profileActive@-@profileRegion@_db,@profileActive@-@profileRegion@_redis,@profileActive@-@profileRegion@_other
```
spring.profiles.active为当前运行环境，通过@profileActive@获取，spring.profiles.region为当前国别，通过@profileRegion@获取。
spring.profiles.include为启动时要包含配置文件，注意加全。

application-*配置文件为该web下的其他配置文件，可通过部署环境和业务进行区分
### config配置类
通过@Value("${data.id}")获取配置文件中的值

### controller
通过@Autowired完成注入

### 启动类
WebBootstrap.java，注意该启动类必须在根目录下，由于profile-web依赖profile-other-jar，而该jar的package目录为com.ttt.springboot.profile.other.jar,boot加载时会扫描根目录下
所有文件，因此启动类所在根目录为com.ttt.springboot.profile才能加载profile-other-jar的类。

## 父pom
### 增加module
```xml
<modules>
    <module>profile-web</module>
    <module>profile-other-jar</module>
</modules>
```
### 增加依赖管理
对版本统一管理
```xml
<!-- 依赖管理，统一版本-->
<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>com.ttt.demo</groupId>
            <artifactId>profile-web</artifactId>
            <version>${project.version}</version>
        </dependency>
        <dependency>
            <groupId>com.ttt.demo</groupId>
            <artifactId>profile-other-jar</artifactId>
            <version>${project.version}</version>
        </dependency>
    </dependencies>
</dependencyManagement>
```
增加依赖管理后，后续项目内依赖不需要写version。
### 配置profile
```xml
<!--profile定义 -->
<profiles>
    <profile>
        <id>dev_zh</id>
        <activation>
            <activeByDefault>true</activeByDefault>
        </activation>
        <properties>
            <profileActive>dev</profileActive>
            <profileRegion>zh</profileRegion>
        </properties>
    </profile>
    <profile>
        <id>prod_zh</id>
        <properties>
            <profileActive>prod</profileActive>
            <profileRegion>zh</profileRegion>
        </properties>
    </profile>
    <profile>
        <id>dev_en</id>
        <activation>
            <activeByDefault>true</activeByDefault>
        </activation>
        <properties>
            <profileActive>dev</profileActive>
            <profileRegion>en</profileRegion>
        </properties>
    </profile>
    <profile>
        <id>prod_en</id>
        <properties>
            <profileActive>prod</profileActive>
            <profileRegion>en</profileRegion>
        </properties>
    </profile>
</profiles>
```
打包时包含的配置文件 
```xml
<build>
<resources>
    <resource>
        <directory>src/main/resources</directory>
        <filtering>true</filtering>
        <excludes>
            <exclude>application*.properties</exclude>
        </excludes>
    </resource>
    <resource>
        <directory>src/main/resources</directory>
        <filtering>true</filtering>
        <includes>
            <include>application.properties</include>
            <include>application-${profileActive}-${profileRegion}_*.properties</include>
        </includes>
    </resource>
</resources>
</build>
```
application.properties最好放在上面。

# 打包支持
通过mvn clean package -P prod_en来指定打包的环境。 不指定-P时，默认打dev_zh相关的配置文件
可通过查看target/classes查看打包的文件内容 。正常情况下，指定-P prod_en时，会只将prod_en的配置文件打入jar包中。

# 启动bootstrap
以-P prod_en打包时
localhost:8082/test3 返回为other_id_prod_en,other_key_prod_en，表示取的是prod环境的值。
不加-P时
localhost:8082/test3 返回为other_id_dev,other_key_dev，表示取的是dev环境的值。
