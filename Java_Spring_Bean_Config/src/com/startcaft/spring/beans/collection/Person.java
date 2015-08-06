package com.startcaft.spring.beans.collection;

import java.util.List;

import com.startcaft.spring.beans.Car;

public class Person {
	private String name;
	private int age;
	private List<Car> cars;
	
	public List<Car> getCars() {
		return cars;
	}
	public void setCars(List<Car> cars) {
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
		return "Person [name=" + name + ", age=" + age + ", cars=" + cars + "]";
	}
}
