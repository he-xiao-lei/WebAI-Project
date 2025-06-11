package cloud.hexiaolei.webaiproject.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Slf4j
@Order(8)
@Component
//@Aspect
public class MyAspect2 {
    //前置通知
    @Before("execution(* cloud.hexiaolei.webaiproject.service.impl.*.*(..))")
    public void before() {
        log.info("MyAspect2 -> before ...");
    }

    //后置通知
    @After("execution(* cloud.hexiaolei.webaiproject.service.impl.*.*(..))")
    public void after() {
        log.info("MyAspect2 -> after ...");
    }
}
