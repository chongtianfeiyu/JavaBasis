package com.startcaft.java.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解
 */
@Documented
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(value=RetentionPolicy.RUNTIME)
public @interface MyAnnotation {
	
	String value() default "hello";
}
