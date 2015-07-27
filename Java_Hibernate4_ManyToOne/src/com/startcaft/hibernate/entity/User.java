package com.startcaft.hibernate.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="tb_User")
public class User {	//一个Group包含多个User，一个User只能属于一个Group。Group为一的一方，User为多的一方。
	
	private int id;
	private String name;
	private Group group;
	
	/*
	 * @ManyToOne 注解用来映射 多对一 关系。【一的一方就不需要关联关系了】
	 * @JoinColumn 注解用来设置外键列。
	 */
	@ManyToOne
	@JoinColumn(name="groupId")
	public Group getGroup() {
		return group;
	}
	public void setGroup(Group group) {
		this.group = group;
	}
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name="user_name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
