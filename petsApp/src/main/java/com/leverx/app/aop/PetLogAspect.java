package com.leverx.app.aop;

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
public class PetLogAspect {

    @Pointcut("execution(public * com.leverx.app.service.impl.PetServiceImpl.*(..))")
    public void petServiceExec() {
    }

    @AfterReturning("petServiceExec()")
    public void success(JoinPoint jp) {
        log.info("method " + jp.toString() + "was successful");
    }

    @AfterThrowing("petServiceExec()")
    public void exception(JoinPoint jp) {
        log.error("exception occurred at " + jp.toString());
    }
}
