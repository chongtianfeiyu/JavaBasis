package com.hibernate.model;

import javax.persistence.Column;
import javax.persistence.Entity;//注意，注解都是使用javax.persistence包里的对象，JPA标准。
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Tb_Teacher")
public class Teacher {
	
	private int id;
	private int age;
	private String name;
	
	//注意，jpa注解，一般写在get放上，切记保持属性与set，get方法的后缀名一直。
	
	@Id
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	@Column(name="UserName")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
