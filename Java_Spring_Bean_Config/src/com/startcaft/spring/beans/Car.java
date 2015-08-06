package com.startcaft.spring.beans;

public class Car {
	private String brand;
	private String corp;
	private double price;
	private int maxSpeed;
	
	public String getBrand() {
		return brand;
	}


	public void setBrand(String brand) {
		this.brand = brand;
	}


	public String getCorp() {
		return corp;
	}


	public void setCorp(String corp) {
		this.corp = corp;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public int getMaxSpeed() {
		return maxSpeed;
	}


	public void setMaxSpeed(int maxSpeed) {
		this.maxSpeed = maxSpeed;
	}


	//构造1
	public Car(String brand, String corp, double price) {
		super();
		this.brand = brand;
		this.corp = corp;
		this.price = price;
	}
	
	
	//构造2
	public Car(String brand, String corp, int maxSpeed) {
		super();
		this.brand = brand;
		this.corp = corp;
		this.maxSpeed = maxSpeed;
	}



	@Override
	public String toString() {
		return "Car [brand=" + brand + ", corp=" + corp + ", price=" + price
				+ ", maxSpeed=" + maxSpeed + "]";
	}
}
