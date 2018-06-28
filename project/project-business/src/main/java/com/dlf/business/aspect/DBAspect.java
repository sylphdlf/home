package com.dlf.business.aspect;

import org.apache.commons.beanutils.PropertyUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Date;

@Aspect
@Component
public class DBAspect {

    @Pointcut("execution(* com.dlf.model.mapper.*.insert(..))")
    private void beforeInsert(){};

    /**
     * 对于插入语句自动添加create_time, is_deleted
     * @param jp
     */
    @Before("beforeInsert()")
    public void insert(JoinPoint jp){
        if (jp.getArgs() == null) {
            return;
        }
        for(Object bean : jp.getArgs()){
            if(bean instanceof String){
                continue;
            }
            try {
                PropertyUtils.setProperty(bean, "createTime", new Date());
                PropertyUtils.setProperty(bean, "isDeleted", (byte)0);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}