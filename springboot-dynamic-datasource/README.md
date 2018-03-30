# dymamic-datasource
- 动态数据源切换。
- 支持事务，不支持分布式事务。

# 说明
## 配置切面
```java
@Aspect
@Order(-1)
@Component
public class DynamicDataSourceAspect{
    private static Logger log= LoggerFactory.getLogger(DynamicDataSourceAspect.class);

    /**
     * 切面放在service方法上，所以这里要配置AOP切面的切入点
     */
    @Pointcut("execution( * com.ttt.dynamic.datasource.service..*.*(..))")
    public void dataSourcePointCut() {
    }

    @Before("dataSourcePointCut()")
    public void before(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature)signature;
        Method method = methodSignature.getMethod();
        try {
            //如果方法上存在切换数据源的注解，则根据注解内容进行数据源切换
            if (method != null && method.isAnnotationPresent(TargetDataSource.class)) {
                TargetDataSource data = method.getAnnotation(TargetDataSource.class);
                String dataSourceName = data.value();
                DynamicDataSourceHolder.putDataSource(dataSourceName);
                log.debug("current thread " + Thread.currentThread().getName() + " add " + dataSourceName + " to ThreadLocal");
               // }
               } else {
                log.debug("switch datasource fail,use default");
            }
        } catch (Exception e) {
            log.error("current thread " + Thread.currentThread().getName() + " add data to ThreadLocal error", e);
        }
    }

    /**
     * 执行完切面后，将线程共享中的数据源名称清空
     * @param joinPoint
     */
    @After("dataSourcePointCut()")
    public void after(JoinPoint joinPoint){
        DynamicDataSourceHolder.removeDataSource();
    }
}
```
根据注解内容查找对应的数据源，放入threadlocal中。
## Threadlocal配置
存入当前使用的数据源，线程安全。
```java
public class DynamicDataSourceHolder {
    /**
     * 本地线程共享对象
     */
    private static final ThreadLocal<String> THREAD_LOCAL = new ThreadLocal<>();

    public static void putDataSource(String name) {
        THREAD_LOCAL.set(name);
    }

    public static String getDataSource() {
        return THREAD_LOCAL.get();
    }

    public static void removeDataSource() {
        THREAD_LOCAL.remove();
    }
}
```
## 继承AbstractRoutingDataSource对数据源进行查找
```java
public class MasterSlaveRoutingDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceHolder.getDataSource();
    }
}
```
## 多数据源读取配置
```java
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author tutingting
 * @date 2018/3/24 下午5:51
 */
@Configuration
@EnableTransactionManagement
@MapperScan("com.ttt.dynamic.datasource.dao.mapper")
public class DynamicDataSourceConfiguration {
    private static Logger log= LoggerFactory.getLogger(DynamicDataSourceConfiguration.class);
    static String mapperLocations="classpath:mapper/*Mapper.xml";

    @Value("${druid.type}")
    private Class<? extends DataSource> dataSourceType;

    @Primary
    @Bean(name = "studyMasterDataSource")
    @ConfigurationProperties(prefix = "druid.study.master")
    public DataSource studyMasterDataSource() {
        return DataSourceBuilder.create().type(dataSourceType).build();
    }

    @Bean(name = "studySlaveDataSource")
    @ConfigurationProperties(prefix = "druid.study.slave")
    public DataSource studySlaveDataSource1() {
        return DataSourceBuilder.create().type(dataSourceType).build();
    }

    @Bean(name = "productMasterDataSource")
    @ConfigurationProperties(prefix = "druid.product.master")
    public DataSource productMasterDataSource() {
        return DataSourceBuilder.create().type(dataSourceType).build();
    }

    @Bean(name = "productSlaveDataSource")
    @ConfigurationProperties(prefix = "druid.product.slave")
    public DataSource productSlaveDataSource1() {
        return DataSourceBuilder.create().type(dataSourceType).build();
    }

    /**
     * 将所有数据源放入AbstractRoutingDataSource中，同时设置默认数据源
     * @return
     */
    @Bean(name = "dataSource")
    public AbstractRoutingDataSource dataSource() {
        MasterSlaveRoutingDataSource proxy = new MasterSlaveRoutingDataSource();
        Map<Object, Object> targetDataResources = new HashMap<>();
        targetDataResources.put("productMasterDataSource", productMasterDataSource());
        targetDataResources.put("productSlaveDataSource", productSlaveDataSource1());
        targetDataResources.put("studyMasterDataSource", studyMasterDataSource());
        targetDataResources.put("studySlaveDataSource", studySlaveDataSource1());
        proxy.setDefaultTargetDataSource(studyMasterDataSource());
        proxy.setTargetDataSources(targetDataResources);
        proxy.afterPropertiesSet();
        return proxy;
    }

    /**
     * 配置mapper所在location. 类上通过mapperscan配置mapper所在包。也可在appliation.properties中配置
     * @param dataSource
     * @return
     * @throws Exception
     */
    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory masterSqlSessionFactory(@Qualifier("dataSource") AbstractRoutingDataSource dataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(mapperLocations));
        return sessionFactory.getObject();
    }
}
```

@EnableTransactionManagement注解表明支持事务。
@Primary指定主数据源，一定要配置
@ConfigurationProperties(prefix = "druid.study.master")指明配置读取的前缀。

## 配置注解
service方法上有注解时说明需要切换数据源
```java
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface TargetDataSource {
    String value();
}
```
## 建立entity及mapper。
。。。

## service方法上实现数据源切换
```java
import City;
import Product;
import CityMapper;
import ProductMapper;
import com.ttt.datasource.dynamic.db.annotation.*;
import SpringContextUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author tutingting
 * @date 2018/3/24 下午6:45
 * 由于事务不会传递，对于某一方法中单独的写，可以加事务控制。
 * 如果某一方法中有读又有写，不建议在整个方法中加事务，如果非要加事物，必须 设置该方法中读的事务传播策略为NOT_SUPPORTED
 */
@Service
public class CityService extends  CoreService{
    @Resource
    private CityMapper cityMapper;
    @Resource
    private ProductService productService;
    @Resource
    private ProductMapper productMapper;

    @TargetDataSource("studySlaveDataSource")
    public List<City> getCityList() {
        return cityMapper.listSlave();
    }

    @TargetDataSource("studyMasterDataSource")
    public List<City> getCityList2() {
        return cityMapper.listMaster();
    }

    /**
     * 主库写，异常会回滚,事务生效
     * @throws Exception
     */
    @TargetDataSource("studyMasterDataSource")
    @Transactional(propagation= Propagation.REQUIRED,rollbackFor = Exception.class)
    public void insertCity() throws  Exception{
        City city=new City();
        city.setId(6L);
        city.setName("test");
        city.setState("test");
        cityMapper.insert(city);
  //     throw new Exception();
    }

    /**
     * 从不同数据库读。非事务
     * 不能直接通过this.**()调用同一service里的方法，如果采用此调用方式 ，数据源不会切换.
     * 如果需要切换数据源，且需要调用同一service里的方法，须通过getService().**()调用。
     * @return
     */
    @TargetDataSource("studyMasterDataSource")
    public List<Product> readFromDifferDs(){
        getCityList();
        return ((CityService)getService()).getProductMaster();
     //   return productService.getProductMaster();
    }

    /**
     * 主库写，从库（不同数据库）读,如果需要在整个方法上配置事务，读的方法必须配置传播策略为NOT_SUPPORTED，否则还是会走原来的连接。
     * @return
     * @throws Exception
     */
    @TargetDataSource("studyMasterDataSource")
    @Transactional
    public List<Product> writeAndRead() throws Exception{
        City city=new City();
        city.setId(7L);
        city.setName("test");
        city.setState("test");
        cityMapper.insert(city);
        return ((CityService)getService()).getProductMaster();
      //  throw new Exception();
    }

    /**
     * 主库写，从库（同一数据库）读，如果不用在整个方法上配事务，只在该方法中写的部分配置事务，由于事务不会向上传递，
     * 因此，该方法中的读不用配置事务传播策略为NOT_SUPPORTED
     * @return
     * @throws Exception
     */
    @TargetDataSource("studyMasterDataSource")
    public List<City> writeAndReadCity() throws Exception{
        ((CityService)getService()).insertCity();
        return ((CityService)getService()).getCityList();
        //  throw new Exception();
    }

    /**
     * 主库写，通过 service读
     * @return
     * @throws Exception
     */
    @TargetDataSource("studyMasterDataSource")
    public List<Product> writeAndReadByService() throws Exception{
        this.insertCity();
        return productService.getProductMaster();
    }

    /**
     * 如果写不同库，不支持分布式事物，不要在整个方法上加事务。
     * @throws Exception
     */
    @TargetDataSource("studyMasterDataSource")
    public void writeDifDs() throws Exception{
        ((CityService)getService()).insertCity();
        productService.insertProduct();
    }

    /**
     * 如果写不同库，如果非要在整个方法上加事务，所有写方法的事务传播策略须为REQUIRES_NEW
     * @throws Exception
     */
    @TargetDataSource("studyMasterDataSource")
    @Transactional
    public void writeDifDsNew() throws Exception{
        ((CityService)getService()).insertCity();
        productService.insertProductNew();
    }

    @TargetDataSource("productMasterDataSource")
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<Product> getProductMaster() {
        return productMapper.listMaster();
    }
}
```
注意几点：
- 方法上有@TargetDataSource("dsName")时会进行数据源切换
- 需要支持事务时，请在方法上加上 @Transactional。推荐直接在方法上加事务控制，不要直接在类上加
- 一个方法中需要读取不同数据库时，不能直接通过this.**()调用同一service里的方法，如果采用此调用方式 ，数据源不会切换；如果需要切换数据源，且需要调用同一service里的方法，须通过getService().**()调用。
  - 原因：切面是走的代理，不是调用的实际对象。因此需要通过getService()获取对象实例。
- 一个方法中有读有写，如果需要在整个方法上配置事务，读的方法必须配置传播策略为NOT_SUPPORTED，否则还是会走原来的连接，不会切换数据源
- 一个方法中有读有写，如果不用在整个方法上配事务，只在该方法中写的部分配置事务，由于事务不会向上传递，因此，该方法中的读不用配置事务传播策略为NOT_SUPPORTED
- 一个方法中需要写不同数据库，不支持分布式事物，不要在整个方法上加事务。只需要在不同的子写方法中配置@Transactional即可
- 一个方法中需要写不同数据库，不支持分布式事物，如果非要在整个方法上加事务。所有写方法的事务传播策略须为REQUIRES_NEW
- 分布式事务可参考springboot+Atomkos或者其他技术

## 启动类
引用动态数据源，不要加载自带的数据源
```java
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@Import({DynamicDataSourceConfiguration.class}) // 注册动态多数据源
public class WebBootstrap {
    public static void main(String[] args) {
        SpringApplication.run(WebBootstrap.class, args);
    }
}
```
