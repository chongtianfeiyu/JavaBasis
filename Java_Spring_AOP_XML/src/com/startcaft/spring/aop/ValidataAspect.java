package com.startcaft.spring.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;

public class ValidataAspect {
	
	public void validataArgs(JoinPoint joinPoint){
		System.out.println("--->validata: "+ Arrays.asList(joinPoint.getArgs()));
	}
}
