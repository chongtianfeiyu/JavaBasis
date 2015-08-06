package com.startcaft.spring.cycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * 自定义Bean的后置处理器
 * @author Administrator
 *
 */
public class MyBeanPostProcessor implements BeanPostProcessor {

	@Override
	public Object postProcessAfterInitialization(Object arg0, String arg1)
			throws BeansException {
		System.out.println("postProcessAfterInitialization: " + arg0 + "," + arg1);
		
		//偷梁换柱
		Car car = new Car();
		car.setBrand("Ford");
		return car;
	}

	@Override
	public Object postProcessBeforeInitialization(Object arg0, String arg1)
			throws BeansException {
		System.out.println("postProcessBeforeInitialization: " + arg0 + "," + arg1);
		return arg0;
	}

}
