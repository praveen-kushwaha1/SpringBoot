package com.spring.aop;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* com.spring.service.PaymentService.makePayment(..))")
    public void logBeforePayment() {
        System.out.println("Before making payment: Payment process is starting...");
    }
}
