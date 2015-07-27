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
	 * @OneToOne��������֮���һ��һ�Ĺ���
	 * 
	 * cascade���������ԣ����������������ĳ�ֲ���ʱ���Ƿ������ص��Ӷ���Ҳ����Ӧ�Ĳ�������ѡֵ5����
	 * ----CascadeType.ALL			�ҹ�һ������
	 * ----CascadeType.MERGE		��������
	 * ----CascadeType.REFRESH		����ˢ��
	 * ----CascadeType.REMOVE		����ɾ��
	 * ----CascadeType.PERSIST		�����½�
	 * 
	 * fetch��ץȡ���ԣ�����2����ѡֵ��
	 * ----FetchType.LAZY		�ӳ�ץȡ(�ӳ�ץȡ�ܹ���֤Ӧ��ֻ������Ҫ��ʱ���ȥ���ݿ�ץȡ��Ӧ�����ݼ�¼)
	 * ----FetchType.EAGER		����ץȡ
	 * 
	 * @JoinColumn ����������������ƣ����û�����ø�ע�⣬�����ʹ��Ĭ�����ƣ������ǣ���������Ķ�����(����ĸСд����������)_id��
	 * ----name����		�����������
	 * ----unique����	����ΨһԼ��
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
