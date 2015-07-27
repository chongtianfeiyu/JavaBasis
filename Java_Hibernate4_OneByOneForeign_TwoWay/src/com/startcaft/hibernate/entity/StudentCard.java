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
	 * �����˫��һ��һ��������ô�� �������У��������һ���Դ� ��������á�
	 * ����@OneToOneע��ӳ�䣻
	 * mappedBy:�൱��xml��ʽ���õ�inverse="true"����ʾ����֮��Ĺ�ϵ��ά��Ȩ�����Է���
	 * 		�Է����ǹ�ϵ������ά���ߣ�ӵ��ָ��Ȩ��
	 * 
	 * ����˫������У����ҽ���һ���������(owner)���ڡ���
	 * 		���ڲ���Ҫά��������ϵ�ı���ͨ��mappedBy��������
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
