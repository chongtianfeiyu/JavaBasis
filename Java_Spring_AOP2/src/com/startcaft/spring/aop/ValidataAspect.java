package com.startcaft.spring.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

//@order 注解为切面进行排序，值越小，优先级越高
@Order(1)
@Aspect
@Component
public class ValidataAspect {
	
	@Before("com.startcaft.spring.aop.LogginAspect.declareJoinPointExpression()")
	public void validataArgs(JoinPoint joinPoint){
		System.out.println("--->validata: "+ Arrays.asList(joinPoint.getArgs()));
	}
}
