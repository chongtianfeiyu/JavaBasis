package com.startcaft.spring.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;

public class LogginAspect {
	
	public void beforeMethod(JoinPoint joinPoint){
		String methodName = joinPoint.getSignature().getName();
		Object[] args = joinPoint.getArgs();
		System.out.println("the method " + methodName + "begins with" + Arrays.asList(args));
	}
	
	public void afterMethod(JoinPoint joinPoint){
		String methodName = joinPoint.getSignature().getName();
		System.out.println("the method " + methodName + " end");
	}
	
	public void afterReturningMethod(JoinPoint joinPoint,Object result){
		String methodName = joinPoint.getSignature().getName();
		System.out.println("the method " + methodName + " result is " + result);
	}
	
	public void afterThrowingMethod(JoinPoint joinPoint,Exception ex){
		String methodName = joinPoint.getSignature().getName();
		System.out.println("the method " + methodName + " throw Exception:" + ex);
	}
	
//	public Object aroundMethod(ProceedingJoinPoint pJd){
//		
//		Object result = null;
//		String methodName = pJd.getSignature().getName();
//		Object[] args = pJd.getArgs();
//		
//		try {
//			//前置通知
//			System.out.println("The method " + methodName + " begins with " + Arrays.asList(args));
//			//执行目标方法
//			result = pJd.proceed();
//			//返回通知
//			System.out.println("Teh method " + methodName + " end with " + result);
//		} catch (Throwable e) {
//			System.out.println("The method " + methodName + " throw Exception: " + e);
//			throw new RuntimeException(e);
//		}
//		//后置通知
//		System.out.println("The method " + methodName + " end");
//		
//		return result;
//	}
}
