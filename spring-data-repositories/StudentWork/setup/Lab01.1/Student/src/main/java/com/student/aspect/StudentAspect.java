package com.student.aspect;

import javax.inject.Named;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
@Named
public class StudentAspect {

    private Logger LOG = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @Pointcut("execution(* com.student.service.*.*(..))")
    public void log(){}

    @Before("log()")
    public void beforeAdvice(JoinPoint jp){
        LOG.info("Invoked Method before -> " + jp.getSignature().getName());
    }
    
    @After("log()")
    public void afterAdvice(JoinPoint jp){
        LOG.info("Invoked Method After -> " + jp.getSignature().getName());
    }

}
