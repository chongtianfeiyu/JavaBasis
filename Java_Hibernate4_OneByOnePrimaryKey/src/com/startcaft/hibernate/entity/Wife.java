package com.startcaft.hibernate.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name="tb_wife")
public class Wife {
	
	private int id;
	private String name;
	private Husband husband;
	
	/*
	 * wife的ID是根据husband的ID来赋值的，
	 * 这里定义了一个名称为 pkGenerator，策略为 foreign 的ID生成策略；
	 * 参数中指定wife的ID使用的是husband对象中的ID。
	 * 
	 * @GeneratedValue中指定我们自定义的ID生成策略。
	 */
	@Id
	@GenericGenerator(name="pkGenerator",strategy="foreign",parameters={@Parameter(name="property",value="husband")})
	@GeneratedValue(generator="pkGenerator")
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
	
	@OneToOne(cascade=CascadeType.ALL,mappedBy="wife")
	public Husband getHusband() {
		return husband;
	}
	public void setHusband(Husband husband) {
		this.husband = husband;
	}
}
