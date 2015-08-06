package com.startcaft.spring.beans.collection;

import java.util.Map;

import com.startcaft.spring.beans.Car;

public class NewPerson {
	private String name;
	private int age;
	private Map<String,Car> cars;

	public Map<String, Car> getCars() {
		return cars;
	}
	public void setCars(Map<String, Car> cars) {
		this.cars = cars;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	@Override
	public String toString() {
		return "NewPerson [name=" + name + ", age=" + age + ", cars=" + cars
				+ "]";
	}
}
