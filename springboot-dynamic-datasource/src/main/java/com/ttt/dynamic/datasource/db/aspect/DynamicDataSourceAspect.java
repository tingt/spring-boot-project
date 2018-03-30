package com.ttt.dynamic.datasource.db.aspect;

import com.ttt.dynamic.datasource.db.annotation.TargetDataSource;
import com.ttt.dynamic.datasource.db.config.DynamicDataSourceHolder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author tutingting
 * @date 2018/3/26 下午4:27
 */
@Aspect
@Order(-1)
@Component
public class DynamicDataSourceAspect{
    private static Logger log= LoggerFactory.getLogger(DynamicDataSourceAspect.class);

    /**
     * 切面放在service方法上，所以这里要配置AOP切面的切入点，多个package可以用||分隔
     */
    @Pointcut("execution( * com.ttt.dynamic.datasource.service.*.*(..)) || execution(* com.ttt.dynamic.datasource.service2..*.*(..))")
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
