package com.dlf.business.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DBAspect {

    @Before(value = "execution(* com.dlf.business.manager.*.*(..))" )
    public void beforeInsert(JoinPoint joinPoint){
        System.out.println(joinPoint);
    }
}