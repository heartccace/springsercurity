package com.web.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author heartccace
 * @create 2020-05-29 11:37
 * @Description 参见 {@link com.web.filter.TimeFilter}
 * 异常处理顺序 TimeAspect ->  {@link com.web.interceptor.TimeInterceptor}
 * @Version 1.0
 */
@Aspect
@Component
public class TimeAspect {

    //定义一个切入点
    @Pointcut("execution(* com.web.controller.HelloController.*(..))")
    public void pointcut() {}

    // @Around("execution(* com.web.controller.HelloController.*(..))")
    @Around("pointcut()")
    public Object handleControllerMethod(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("time aspect start");
        Object[] args = pjp.getArgs();
        for (Object arg : args) {
            System.out.println("arg is "+arg);
        }

        long start = new Date().getTime();

        Object object = pjp.proceed();
        System.out.println("time aspect 耗时:"+ (new Date().getTime() - start));
        System.out.println("time aspect end");
        return object;
    }

    /**
     * 前置通知
     */
    @Before(value="pointcut()")
    public void before(){
        System.out.println("前置通知....");
    }

    /**
     * 后置通知
     * returnVal,切点方法执行后的返回值
     */
    @AfterReturning(value="pointcut()",returning = "returnVal")
    public void AfterReturning(Object returnVal){
        System.out.println("后置通知...."+returnVal);
    }

    /**
     * 抛出通知
     * @param e
     */
    @AfterThrowing(value="pointcut()",throwing = "e")
    public void afterThrowable(Throwable e){
        System.out.println("出现异常:msg="+e.getMessage());
    }

    /**
     * 无论什么情况下都会执行的方法
     */
    @After(value="pointcut()")
    public void after(){
        System.out.println("最终通知....");
    }
}
