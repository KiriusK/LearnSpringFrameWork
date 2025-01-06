package ru.gb.homework8.aspects;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ExceptionAspect {



    @AfterThrowing(pointcut = "execution(* ru.gb.homework8.services.*.*(..))", throwing = "ex")
    public void servicesExceptionHandler(JoinPoint joinPoint, Exception ex) {
        System.out.println("Метод " + joinPoint.getSignature().getName() + " вызвал ошибку " + ex.getMessage());

    }

}
