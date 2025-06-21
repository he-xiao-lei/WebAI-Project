package cloud.hexiaolei.webaiproject.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class TestAspect {


    //    @Around("execution(* cloud.hexiaolei.webaiproject.controller.*.save(..)) ||" +
//            "execution(* cloud.hexiaolei.webaiproject.controller.*.update(..)) ||" +
//            "execution(* cloud.hexiaolei.webaiproject.controller.*.delete(..))")
    @Around("@annotation(cloud.hexiaolei.webaiproject.anno.LogOperation)")
    public Object recordLogInfo(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = pjp.proceed();
        long end = System.currentTimeMillis();
        System.out.println("耗时为" + (end - start));
        return result;
    }
}


