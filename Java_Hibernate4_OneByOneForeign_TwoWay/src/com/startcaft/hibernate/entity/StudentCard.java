package com.startcaft.hibernate.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="Tb_student_card")
public class StudentCard implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private int id;
	private Date date;
	private Student student;
	
	/*
	 * 如果是双向一对一关联，那么主 对象类中，必须添加一个对从 对象的引用。
	 * 并用@OneToOne注解映射；
	 * mappedBy:相当于xml方式配置的inverse="true"，表示将表之间的关系的维护权交给对方，
	 * 		对方才是关系的真正维护者，拥有指导权。
	 * 
	 * 【在双向关联中，有且仅有一段是主体端(owner)存在】，
	 * 		对于不需要维护关联关系的表则通过mappedBy属性声明
	 */
	@OneToOne(mappedBy="studentCard")
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(nullable=false)
	@Temporal(TemporalType.DATE)
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
}
