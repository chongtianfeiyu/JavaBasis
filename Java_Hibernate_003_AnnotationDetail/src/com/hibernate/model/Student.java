package com.hibernate.model;

import java.beans.Transient;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

//将一个类声明为一个实体bean(即一个可持久化的POJO)
@Entity
//Table 如果类名和表明一致时，可以不写
@Table(name="Tb_Student")
public class Student {
	
	private int studentId;
	private String name;
	//注意这个Date类型，一般使用java.util.Date类而不是java.sql.Date类
	private Date birthday;
	private String myWifeName;
	private Sex sex;
	
	//@Id注解用于映射主键列
	//@GeneratedValue注解用来主键的生成策略
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	
	//实体bean中的所有非static非transient的属性都可以被持久化,除非你将其注解为@Transient
	//所有没有定义注解的属性等价于在其上面添加了@Basic注解
	//所以这里不写@Basic也是OK的
	//@Column注解映射属性到列，并可以覆盖默认值，比如映射列的类名，类型等等
	@Basic
	@Column(name="Student_Name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	//在数据库中,表示时间类型的数据有DATE, TIME, 和 TIMESTAMP三种精度
	//使用@Temporal注解可以调整时间的精度
	@Temporal(TemporalType.DATE)
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	//@Transient注解表示不映射该属性
	@Transient
	public String getMyWifeName() {
		return myWifeName;
	}
	public void setMyWifeName(String myWifeName) {
		this.myWifeName = myWifeName;
	}
	
	//@Enumerated用于映射枚举类型
	//EnumType.STRING存的是枚举的字符串形式，EnumType.ORDINAL存的是枚举的Integer形式
	@Enumerated(EnumType.ORDINAL)
	public Sex getSex() {
		return sex;
	}
	public void setSex(Sex sex) {
		this.sex = sex;
	}
	
}
