package com.leverx.app.aop.logging;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Log4j2
public class CommonLogAspect {

    @Pointcut("execution(public * com.leverx.app.service.impl.CommonServiceImpl.*(..))")
    public void CommonServiceExec() {
    }

    @AfterReturning("CommonServiceExec()")
    public void success(JoinPoint jp) {
        log.info("method " + jp.toString() + "was successful");
    }

    @AfterThrowing("CommonServiceExec()")
    public void exception(JoinPoint jp) {
        log.error("exception occurred at " + jp.toString());
    }

}
