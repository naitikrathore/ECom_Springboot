package com.codeSnippet.CodeSnippet.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

    //Pointcuts
    /**
     * A Pointcut
     * this @Before tells before executing the ProductService.getAllProduct(..) execute this function
     * the first star(*) is return type and then package where the method located then the class name and
     * then the function name and followed by function name (..) this is the parameters of function and double dot
     * indicated i don't care whatever is parameter.
     *
     * And these logging functions are called pointcut
     */
    @Before("execution(* com.codeSnippet.CodeSnippet.service.ProductService.getAllProduct(..))")
    public void logBeforeGetAllProduct(){
        System.out.println("This should be called BEFORE executing the getAllProduct()");
    }

    @After("execution(* com.codeSnippet.CodeSnippet.service.ProductService.getAllProduct(..))")
    public void logAfterGetAllProduct(){
        System.out.println("This should be called AFTER executing the getAllProduct()");
    }

    //log before executing any function
    @Before("execution(* com.codeSnippet.CodeSnippet.service.ProductService.*(..))")
    public void logBeforeProductService(){
        System.out.println("This should be called BEFORE executing any function of ProductService");
    }

    @After("execution(* com.codeSnippet.CodeSnippet.service.ProductService.*(..))")
    public void logAfterProductService(){
        System.out.println("This should be called AFTER executing any function of ProductService");
    }


    /**
     * This is one of the most powerful advice in AOP, It's like a wrapper around a method
     * it also gives the result of method call so you can log that as well
     */
    @Around("execution(* com.codeSnippet.CodeSnippet.service.ProductService.getProductByName(..))")
    public Object logAroundGetProductById(ProceedingJoinPoint jointPoint) throws Throwable{
        System.out.println("This should be called BEFORE executing getProductByName() of ProductService");
        long start = System.currentTimeMillis();

        // Proceed to Actual Method
        Object result = jointPoint.proceed();

        long end = System.currentTimeMillis();
        System.out.println("This should be called AFTER executing getProductByName() of ProductService and time taken " + (end - start) + " ms");
        return result;
    }
}
