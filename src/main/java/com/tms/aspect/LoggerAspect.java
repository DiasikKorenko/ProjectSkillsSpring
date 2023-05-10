//package com.tms.aspect;
//
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.*;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//
//import java.time.LocalTime;
//import java.util.Locale;
//
//@Aspect
//@Component
//public class LoggerAspect {
//
//    private final Logger log = LoggerFactory.getLogger(this.getClass());
///*
//указываем когда применяем логирование!
//    @Before()- до выполнения метода
//    @After()-после выполнения метода
//    @Around()-выполение логирования до метода и после(его выполения)
//    @AfterReturning()-когда тебе метод,что-то вернул сразу выполни логирование
//    @AfterThrowing()-после того как в методе выпадет ошибка,выполни метод логирования
//*/
//
// /*   @Before("within(com.tms.*)")
//    public void getLogBefore() {
//        log.info("Method started!!!");
//        System.out.println("doing getLog...aspect");
//    }
//
//    @After("within(com.tms.*)")
//    public void getLogAfter(JoinPoint joinPoint) {
//        log.info("Method"+joinPoint.getSignature()+" finished!!!");
//        System.out.println("doing getLog...aspect");
//    }
//
//    @AfterReturning(value = "within(com.tms.*)",returning = "result")
//    public void getLogAfterReturning(Object result) {
//        log.info("log after returning!!! "+ result);
//        System.out.println("doing getLog...aspect");
//    }
//
//    @AfterThrowing(value = "within(com.tms.SomeLogic)",throwing = "err")
//    public void getLogAfterThrowing(Throwable err) {
//        log.warn("WE HAVE THROW!!! "+ err);
//
//    }*/
//
//  /*  @Around("execution(public * com.tms.SomeLogic.*(String))")
//    public void getLogAround(ProceedingJoinPoint joinPoint) throws Throwable {
//        LocalTime start = LocalTime.now();
//        joinPoint.proceed();
//        LocalTime end = LocalTime.now();
//        log.info("Method worked time: " + (end.getNano() - start.getNano()));
//    }*/
//
//    @Around("@annotation(com.tms.annotation.GetTimeAnnotation)")
//    public void getLogAround(ProceedingJoinPoint joinPoint) throws Throwable {
//        LocalTime start = LocalTime.now();
//        joinPoint.proceed();
//        LocalTime end = LocalTime.now();
//        log.info("Method worked time: " + (end.getNano() - start.getNano()));
//    }
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//}
