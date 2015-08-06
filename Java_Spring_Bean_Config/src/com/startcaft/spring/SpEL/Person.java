package com.startcaft.spring.SpEL;

public class Person {
	private String name;
	private Car car;
	
	/**
	 * 引用Car 的price 确定 info
	 * price >= 300000 : 金领
	 * 否则 白领
	 */
	private String info;
	/**
	 * 根据Address 的city确定
	 */
	private String city;
	
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Car getCar() {
		return car;
	}
	public void setCar(Car car) {
		this.car = car;
	}
	@Override
	public String toString() {
		return "Person [name=" + name + ", car=" + car + ", info=" + info
				+ ", city=" + city + "]";
	}
}
