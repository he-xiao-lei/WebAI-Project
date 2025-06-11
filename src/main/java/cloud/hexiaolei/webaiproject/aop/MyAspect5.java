package cloud.hexiaolei.webaiproject.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect
public class MyAspect5 {
    //前置通知
    //@Before("execution(public void cloud.hexiaolei.service.impl.DeptServiceImpl.delete(java.lang.Integer))")
    //@Before("execution(void cloud.hexiaolei.service.impl.DeptServiceImpl.delete(java.lang.Integer))")
    //@Before("execution(void delete(java.lang.Integer))") //包名.类名 强烈不建议省略
    //@Before("execution(* cloud.hexiaolei.service.impl.DeptServiceImpl.delete(java.lang.Integer))")
    //@Before("execution(* com.*.service.impl.DeptServiceImpl.delete(java.lang.Integer))")
    //@Before("execution(* cloud.hexiaolei.service.impl.*.delete(java.lang.Integer))")
    //@Before("execution(* cloud.hexiaolei.service.impl.*.*(java.lang.Integer))")
    //@Before("execution(* cloud.hexiaolei.service.impl.*.*(*))")
    //@Before("execution(* cloud.hexiaolei.service.impl.*.del*(*))")
    //@Before("execution(* cloud.hexiaolei.service.impl.*.*e(*))")
    //@Before("execution(* cloud.hexiaolei.service.impl.DeptServiceImpl.*(..))")
    //@Before("execution(* cloud.hexiaolei.service.*.*(..))")

    //匹配list 与 delete 方法
    //@Before("execution(* cloud.hexiaolei.service.impl.DeptServiceImpl.list(..)) ||" +
    //        "execution(* cloud.hexiaolei.service.impl.DeptServiceImpl.delete(..))")

//    @Before("@annotation(cloud.hexiaolei.anno.LogOperation)")
//    public void before() {
//        log.info("MyAspect5 -> before ...");
//    }

    //    @Before("execution(public void cloud.hexiaolei.webaiproject.service.impl.DeptServiceImpl.deleteDeptById(java.lang.Integer))")
//    @Before("execution(public void cloud.hexiaolei.webaiproject.service.impl.*.*(java.lang.Integer))")
//    @Before("execution(* cloud.hexiaolei.webaiproject.service.impl.DeptServiceImpl.findAll(..)) || " +
//            "execution(* cloud.hexiaolei.webaiproject.service.impl.DeptServiceImpl.deleteDeptById(..))")


    @Before("@annotation(cloud.hexiaolei.webaiproject.anno.LogOperation)")
    public void before() {
        log.info("MyAspect6 -> before..");
    }

}
