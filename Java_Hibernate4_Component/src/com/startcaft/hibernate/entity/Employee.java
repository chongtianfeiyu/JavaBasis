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
	 * @Embedded ע������ӳ���������ʾǶ������ӳ�䡣
	 * 
	 * ���� �����  ������ʵ�����ֻ������ʵ�����һ���֣���˲���Ҫӳ���ļ�����ע�⡣
	 */
	@Embedded
	public Contact getContact() {
		return contact;
	}
	public void setContact(Contact contact) {
		this.contact = contact;
	}
}
