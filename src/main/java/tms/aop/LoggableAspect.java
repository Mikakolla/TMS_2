package tms.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Aspect
public class LoggableAspect {

    @Pointcut("@annotation(tms.aop.Loggable)")
    public void loggableAspect() {}

    @Before("loggableAspect()")
    public void printTimeBeforeMethod(JoinPoint joinPoint) {
        System.out.println("Вызов метода с именем - " + joinPoint.getSignature().getName());
        System.out.println("Время вызова метода - " + new Date());
    }

    @Around("loggableAspect()")
    public void startLoggableAspect(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        proceedingJoinPoint.proceed();

        long finish = System.currentTimeMillis();

        System.out.println("Потручаенное время на выполнение метода - " + (finish - start));
    }
}
