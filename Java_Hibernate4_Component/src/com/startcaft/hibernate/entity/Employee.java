package com.startcaft.hibernate.entity;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_employee")
public class Employee {
	
	private int id;
	private String name;
	private Contact contact;
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	/*
	 * @Embedded 注解用于映射组件，表示嵌入对象的映射。
	 * 
	 * 由于 组件类  并不是实体对象，只是属于实体类的一部分，因此不需要映射文件或者注解。
	 */
	@Embedded
	public Contact getContact() {
		return contact;
	}
	public void setContact(Contact contact) {
		this.contact = contact;
	}
}
