package com.startcaft.spring.factory;

import java.util.HashMap;
import java.util.Map;

/**
 * 实例工厂方法：实例工厂的方法，先需要创建工厂的实例，再调用工厂的实例方法来返回 Bean的实例。
 * @author Administrator
 *
 */
public class InstanceCarFactory {
	
	private Map<String,Car> cars = null;
	
	public InstanceCarFactory(){
		cars = new HashMap<String,Car>();
		cars.put("audi", new Car("audi",300000));
		cars.put("ford", new Car("ford",200000));
	}
	
	public Car getCar(String name){
		return cars.get(name);
	}
}
