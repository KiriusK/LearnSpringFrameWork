package ru.gb.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ConsoleAspects {

    @After("@annotation(ru.gb.annotations.ConsoleLogging)")
    public void consoleLogger(JoinPoint jp) {
        System.out.println("Выполнен метод " + jp.getSignature().getName() + " класса " + jp.getClass());
    }

    @Around("@annotation(ru.gb.annotations.MeasureTime)")
    public Object measureTime(ProceedingJoinPoint jp) {
        long startTime = System.currentTimeMillis();
        Object res;
        try {
            res = jp.proceed();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Время выполнения метода " + jp.getSignature().getName() + " составляет " + (endTime-startTime) + " мс");
        return res;
    }
}
