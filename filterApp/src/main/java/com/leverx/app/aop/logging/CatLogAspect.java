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
public class CatLogAspect {

    @Pointcut("execution(public * com.leverx.app.repository.impl.CatRepositoryImpl.*(..))")
    public void catRepoExec() {
    }

    @Pointcut("execution(public * com.leverx.app.service.impl.CatServiceImpl.*(..))")
    public void catServiceExec() {
    }

    @AfterReturning("catServiceExec()")
    public void successService(JoinPoint jp) {
        log.info("method " + jp.toString() + "was successful");
    }

    @AfterThrowing("catServiceExec()")
    public void exceptionService(JoinPoint jp) {
        log.error("exception occurred at " + jp.toString());
    }

    @AfterReturning("catRepoExec()")
    public void successRepo(JoinPoint jp) {
        log.info("method " + jp.toString() + "was successful");
    }

    @AfterThrowing("catRepoExec()")
    public void exceptionRepo(JoinPoint jp) {
        log.error("exception occurred at " + jp.toString());
    }

}
