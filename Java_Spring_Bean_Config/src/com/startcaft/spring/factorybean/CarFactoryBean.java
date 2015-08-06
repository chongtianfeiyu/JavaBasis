package com.startcaft.spring.factorybean;

import org.springframework.beans.factory.FactoryBean;

/**
 * 自定义FactoryBean，必须实现FactoryBean接口
 * @author Administrator
 *
 */
public class CarFactoryBean implements FactoryBean<Car> {
	
	private String brand;
	
	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	/**
	 * 返回 bean 对象
	 */
	@Override
	public Car getObject() throws Exception {
		return new Car(brand,500000);
	}
	
	/**
	 * 返回 bean 的类型
	 */
	@Override
	public Class<?> getObjectType() {
		return Car.class;
	}
	

	@Override
	public boolean isSingleton() {
		return true;
	}

}
