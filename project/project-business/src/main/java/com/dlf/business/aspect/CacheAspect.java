package com.dlf.business.aspect;

import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;



/**
 * 判断是否从缓存中获取数据
 */
@Aspect
@Component
public class CacheAspect {

    private Logger logger = LoggerFactory.getLogger(CacheAspect.class);

    /**
     * 切get+query+select
     */
    @Pointcut("(execution(* com.dlf.model.mapper.*.getCached*(..)))")
    private void beforeExecute(){};

    @Around("beforeExecute()")
    public Object getCacheResult(ProceedingJoinPoint joinPoint) {
        Object result = null;
        try {
            logger.info("cache_aspect---------------");
//            logger.info(joinPoint.getArgs()[1] + "");
//            logger.info(joinPoint.getSignature().getDeclaringTypeName());
//            logger.info(joinPoint.getKind());
            //有返回参数 则需返回值
            result = joinPoint.proceed();

        } catch (Throwable t) {
            logger.error(t.getMessage());
        }
        return result;
    }
}