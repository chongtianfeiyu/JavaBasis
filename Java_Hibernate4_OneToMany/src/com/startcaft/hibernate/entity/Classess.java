package com.startcaft.hibernate.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="tb_classess")
public class Classess {
	private int id;
	private String name;
	//һ�Զ�ͨ��ʹ��Set�ӿ���ӳ�䣬��ΪHibernate�����Լ���ʵ��(��Ҫ���ӳټ���)
	private Set<Student> students = new HashSet<Student>();
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name="classess_name",nullable=false,length=30)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	/*
	 * ʹ��@OneToMany����һ�Զ��ע��
	 * @JoinColumn������������Ȼ�������м��
	 */
	@OneToMany
	@JoinColumn(name="classessId")
	public Set<Student> getStudents() {
		return students;
	}
	public void setStudents(Set<Student> students) {
		this.students = students;
	}
}
