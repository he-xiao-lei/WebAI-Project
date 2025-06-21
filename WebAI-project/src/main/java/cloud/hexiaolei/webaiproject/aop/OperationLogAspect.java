package cloud.hexiaolei.webaiproject.aop;

import cloud.hexiaolei.webaiproject.Mapper.OperateLogMapper;
import cloud.hexiaolei.webaiproject.pojo.OperateLog;
import cloud.hexiaolei.webaiproject.utils.CurrentHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * 操作日志切面类
 * 用于记录使用@LogOperation注解的方法的执行信息
 */
@Aspect
@Component
@Slf4j
public class OperationLogAspect {

    private final OperateLogMapper operateLogMapper;
    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    @Autowired
    private OperationLogAspect(OperateLogMapper operateLogMapper) {
        this.operateLogMapper = operateLogMapper;
    }

    /**
     * 环绕通知方法
     * 记录操作日志，包括操作时间、目标类名、方法名、参数、返回值和执行耗时。
     *
     * @param joinPoint 连接点对象，包含目标方法的信息
     * @return 方法执行结果
     * @throws Throwable 如果目标方法执行过程中抛出异常，则向外抛出
     */
    @Around("@annotation(cloud.hexiaolei.webaiproject.anno.LogOperation)")
    public Object logOperation(ProceedingJoinPoint joinPoint) throws Throwable {
        // 记录开始时间
        long startTime = System.currentTimeMillis();

        // 执行目标方法
        Object result = joinPoint.proceed();

        // 计算方法执行耗时
        long costTime = System.currentTimeMillis() - startTime;

        // 获取方法签名和相关信息
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String className = signature.getDeclaringTypeName(); // 获取目标类名
        String methodName = signature.getName(); // 获取方法名
        String methodParams = Arrays.toString(joinPoint.getArgs()); // 获取方法参数

        // 创建 OperateLog 对象并填充数据
        OperateLog operateLog = new OperateLog();
        operateLog.setOperateEmpId(getCurrentEmpId());
        operateLog.setOperateTime(LocalDateTime.now()); // 设置操作时间
        operateLog.setClassName(className); // 设置目标类名
        operateLog.setMethodName(methodName); // 设置方法名
        operateLog.setMethodParams(methodParams); // 设置方法参数
        operateLog.setReturnValue(result != null ? result.toString() : "null"); // 设置返回值
        operateLog.setCostTime(costTime); // 设置方法执行耗时
        log.info("记录操作日志{}", operateLog);
        // 调用 Mapper 插入日志数据
        operateLogMapper.insert(operateLog);

        return result;
    }

    private Integer getCurrentEmpId() {
        return CurrentHolder.getCurrentId();
    }
}