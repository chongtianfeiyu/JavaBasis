package com.startcaft.spring.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


@Order(2)
@Aspect
@Component
public class LogginAspect {
	
	/**
	 * 声明连接点表达式，所有的通知方法使用该表达式即可，避免每次输入表达式。
	 * 使用@Pointcut 注解。
	 * 如果该方法与切面不在一个包类，使用全类名
	 */
	@Pointcut("execution(* com.startcaft.spring.aop.*.*(int, int))")
	public void declareJoinPointExpression(){
		
	}
	
	//方法执行之前
	@Before("declareJoinPointExpression()")
	public void beforeMethod(JoinPoint joinPoint){
		String methodName = joinPoint.getSignature().getName();
		Object[] args = joinPoint.getArgs();
		System.out.println("the method " + methodName + "begins with" + Arrays.asList(args));
	}
	
	//方法执行之后【不管方法是否发生异常，通知都会执行】
	@After("declareJoinPointExpression()")
	public void afterMethod(JoinPoint joinPoint){
		String methodName = joinPoint.getSignature().getName();
		System.out.println("the method " + methodName + " end");
	}
	
	//方法正常结束后执行，可以访问到方法的返回值，必须配置@AfterReturning 注解的returning属性。
	@AfterReturning(value="declareJoinPointExpression())"
			,returning="result")
	public void afterReturningMethod(JoinPoint joinPoint,Object result){
		String methodName = joinPoint.getSignature().getName();
		System.out.println("the method " + methodName + " result is " + result);
	}
	
	//方法异常时执行，可以访问到放到抛出的Throw对象，必须配置@AfterThrowing 注解的throwing属性。
	@AfterThrowing(value="declareJoinPointExpression())"
			,throwing="ex")
	public void afterThrowingMethod(JoinPoint joinPoint,Exception ex){
		String methodName = joinPoint.getSignature().getName();
		System.out.println("the method " + methodName + " throw Exception:" + ex);
	}
	
//	/**
//	 *环绕通知,需要携带ProceedingJoinPoint 类型的参数。【功能最强大,但并不等于最常用】
//	 *环绕通知类似于动态代理的全过程：ProceedingJoinPoint 参数可以指定是否执行目标方法,
//	 *【切环绕通知必须有返回值，返回值必须为目标方法的返回值】
//	 */
//	@Around("declareJoinPointExpression()")
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
