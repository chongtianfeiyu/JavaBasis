package com.startcaft.spring.aop.helloworld;

import java.util.Arrays;
import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * 日志切面，把这个类声明为一个切面：
 * 1，把该类放入到IOC容器中
 * 2，再声明为一个切面 Aspect注解
 */
@Aspect
@Component
public class LogginAspect {
	
	/** 声明该方法是一个 前置通知：在目标方法开始之前执行 */
	@Before("execution(* com.startcaft.spring.aop.helloworld.*.*(int, int))")
	public void beforeMethod(JoinPoint joinPoint){
		
		String methodName = joinPoint.getSignature().getName();
		List<Object> args = Arrays.asList(joinPoint.getArgs());
		System.out.println("the method " + methodName + " begins with ：" + args);
	}
	
	/** 声明该通知为一个 后置通知：在目标方法执行后(无论是否发生异常)，执行的通知；后置通知还不能获取返回结果，必须使用返回通知 */
	@After("execution(* com.startcaft.spring.aop.helloworld.*.*(int, int))")
	public void afterMethod(JoinPoint joinPoint){
		String methodName = joinPoint.getSignature().getName();
		System.out.println("the method " + methodName + " end");
	}
}
