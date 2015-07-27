package com.startcaft.hibernate.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Entity;

@Entity
@Table(name="Tb_student")
public class Student implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private int id;
	private String name;
	private StudentCard studentCard;
	
	/*
	 * @OneToOne建立对象之间的一对一的关联
	 * 
	 * cascade：级联策略，即，当对主对象对某种操作时，是否对其相关的子对象也做相应的操作。可选值5个：
	 * ----CascadeType.ALL			囊过一下四项
	 * ----CascadeType.MERGE		级联更新
	 * ----CascadeType.REFRESH		级联刷新
	 * ----CascadeType.REMOVE		级联删除
	 * ----CascadeType.PERSIST		级联新建
	 * 
	 * fetch：抓取策略，它有2个可选值：
	 * ----FetchType.LAZY		延迟抓取(延迟抓取能够保证应用只有在需要的时候才去数据库抓取相应的数据记录)
	 * ----FetchType.EAGER		立即抓取
	 * 
	 * @JoinColumn 用来设置外键的名称，如果没有设置该注解，则外键使用默认名称，规则是：关联的类的短类名(首字母小写，不带包名)_id。
	 * ----name属性		设置外键名称
	 * ----unique属性	设置唯一约束
	 */
	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY,optional=true)
	@JoinColumn(unique=true,name="student_card_id")
	public StudentCard getStudentCard() {
		return studentCard;
	}
	public void setStudentCard(StudentCard studentCard) {
		this.studentCard = studentCard;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(length=20,nullable=false)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
