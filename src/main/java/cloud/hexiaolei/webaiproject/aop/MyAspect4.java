package cloud.hexiaolei.webaiproject.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;

@Slf4j
//@Order(3)
//@Component
//@Aspect
public class MyAspect4 {
    //前置通知
    @Before("execution(* cloud.hexiaolei.webaiproject.service.impl.*.*(..))")
    public void before() {
        log.info("MyAspect4 -> before ...");
    }

    //后置通知
    @After("execution(* cloud.hexiaolei.webaiproject.service.impl..*(..))")
    public void after() {
        log.info("MyAspect4 -> after ...");
    }
}
