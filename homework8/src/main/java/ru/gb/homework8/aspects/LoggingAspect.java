package ru.gb.homework8.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

    @Around("execution(* ru.gb.homework8.controllers.*.*(..))")
    public Object loggingControllers(ProceedingJoinPoint joinPoint) {
        SecurityContext sc = SecurityContextHolder.getContext();
        String userName = sc.getAuthentication().getName();
        System.out.println("Пользователь " + userName + " Вызвал метод " + joinPoint.getSignature().getName());
        Object result;
        try {
            result = joinPoint.proceed();
        } catch (Throwable e) {
            System.out.println("В методе " + joinPoint.getSignature().getName() + " ошибка " + e.getMessage());
            return null;
        }
        System.out.println("Завершение метода " + joinPoint.getSignature().getName());
        return result;
    }


}
