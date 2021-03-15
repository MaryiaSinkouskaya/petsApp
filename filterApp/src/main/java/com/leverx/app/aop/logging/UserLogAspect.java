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
public class UserLogAspect {

    @Pointcut("execution(public * com.leverx.app.repository.impl.destination.UserRepositoryImpl.*(..))")
    public void userRepoExec() {
    }

    @Pointcut("execution(public * com.leverx.app.service.impl.UserServiceImpl.*(..))")
    public void userServiceExec() {
    }

    @AfterReturning("userServiceExec()")
    public void successService(JoinPoint jp) {
        log.info("method " + jp.toString() + "was successful");
    }

    @AfterThrowing("userServiceExec()")
    public void exceptionService(JoinPoint jp) {
        log.error("exception occurred at " + jp.toString());
    }

    @AfterReturning("userRepoExec()")
    public void successRepo(JoinPoint jp) {
        log.info("method " + jp.toString() + "was successful");
    }

    @AfterThrowing("userRepoExec()")
    public void exceptionRepo(JoinPoint jp) {
        log.error("exception occurred at " + jp.toString());
    }

}
