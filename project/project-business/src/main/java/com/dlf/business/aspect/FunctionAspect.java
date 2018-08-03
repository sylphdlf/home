package com.dlf.business.aspect;

import org.apache.commons.beanutils.PropertyUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Date;

@Aspect
@Component
public class FunctionAspect {
    /**
     * 方法的执行时间 注解
     */
    @Pointcut("@annotation(com.dlf.business.anno.ExecuteTimeAnno))")
    private void executeTime(){};

    /**
     * 对于插入语句自动添加create_time, is_deleted
     * @param joinPoint
     */
    @Around("executeTime()")
    public Object calculate(ProceedingJoinPoint joinPoint){
        Object result = null;
        try {
            System.out.println(joinPoint.getSignature());
            long start = System.currentTimeMillis();

            //有返回参数 则需返回值
            result =  joinPoint.proceed();

            long end = System.currentTimeMillis();
            System.out.println("方法名称：" + joinPoint.getSignature());
            System.out.println("总共执行时长" + (end - start) + " 毫秒");
        } catch (Throwable t) {
            System.out.println("出现错误");
        }
        return result;
    }
}