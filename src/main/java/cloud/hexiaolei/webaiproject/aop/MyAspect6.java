package cloud.hexiaolei.webaiproject.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
//@Aspect
@Component
public class MyAspect6 {

    @Before("execution(* cloud.hexiaolei.webaiproject.service.impl.*.*(..))")
    public void before(JoinPoint joinPoint) {
        log.info("before ....");
        //1. 获取目标对象
        Object target = joinPoint.getTarget();
        log.info("获取目标对象: {}", target);

        //2. 获取目标类
        String className = joinPoint.getTarget().getClass().getName();
        log.info("获取目标类: {}", className);

        //3. 获取目标方法
        String methodName = joinPoint.getSignature().getName();
        log.info("获取目标方法: {}", methodName);

        //4. 获取目标方法参数
        Object[] args = joinPoint.getArgs();
        log.info("获取目标方法参数: {}", Arrays.toString(args));
    }

    @Around("execution(* cloud.hexiaolei.webaiproject.service.*.*(..))")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        log.info("around ... before ....");

        Object result = pjp.proceed();

        log.info("around ... after ....");
        return result;
    }

}
