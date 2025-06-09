package cloud.hexiaolei.webaiproject.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class MyAspect {
    //权限修饰符,public 表示可以让其他切面类也使用这个切入点表达式,private表示仅有
    @Pointcut("execution(* cloud.hexiaolei.webaiproject.service.impl.*.*(..))")
    public void pt(){

    }
    //前置通知
    @Before("pt()")
    public void before(){
         log.info("before...");
    }
    //环绕通知 - 目标方法运行之前，后都运行
    @Around("pt()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        log.info("around before...");
        Object result = pjp.proceed();
        log.info("around after...");
        return result;
    }
    //后置通知 - 目标方法运行之后运行，无论是否出现异常都执行
    @After("pt()")
    public void after(){
        log.info("after...");

    }
    //后置通知 - 目标方法返回后运行,出现异常不会运行
    @AfterReturning("pt()")
    public void afterReturning(){
        log.info("afterReturning...");
    }
    // 后置通知 - 目标方法抛出异常后通知
    @AfterThrowing("pt()")
    public void afterThrowing(){
        log.info("afterThrowing...");
    }

}
