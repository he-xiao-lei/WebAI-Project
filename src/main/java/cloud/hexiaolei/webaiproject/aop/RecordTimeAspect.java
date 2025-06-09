package cloud.hexiaolei.webaiproject.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
@Slf4j
@Component
@Aspect//标识当前类是一个AOP类
public class RecordTimeAspect {
    @Around("execution(* cloud.hexiaolei.webaiproject.service.impl.*.*(..))")
    public Object recordTime(ProceedingJoinPoint pjp) throws Throwable {
        long begin = System.currentTimeMillis();
        Object result = pjp.proceed();
        long end = System.currentTimeMillis();
        log.info("{}执行耗时{}ms",pjp.getSignature(),end-begin);
        return result;
    }

}
