package com.dlf.business.aspect;

import com.dlf.model.dto.user.UserReqDTO;
import com.dlf.model.dto.user.UserResDTO;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;

@Aspect
@Component
public class DBAspect {

    private Logger logger = LoggerFactory.getLogger(CacheAspect.class);

    @Pointcut("execution(* com.dlf.model.mapper.*.insert*(..))")
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
                PropertyUtils.setProperty(bean, "isDeleted", 0);
                try {
                    UserResDTO resDTO = (UserResDTO) SecurityUtils.getSubject().getPrincipal();
                    PropertyUtils.setProperty(bean, "createUserId", resDTO.getId());
                }catch (Exception e){
                    logger.error(e.getMessage());
                }
            }catch (Exception e){
                logger.error(e.getMessage());
            }
        }
    }
}