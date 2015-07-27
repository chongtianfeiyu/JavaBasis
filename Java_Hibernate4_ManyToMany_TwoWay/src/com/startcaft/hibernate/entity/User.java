package com.startcaft.hibernate.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="tb_User")
public class User {
	
	private int id;
	private String name;
	private Set<Group> groups = new HashSet<Group>();
	
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
	
	/*
	 * @ManyToMany ��ӳ�� ��Զ� ��ϵ
	 * @JoinTable(name="middle_table_name",	@JoinTableע���name������ָ���м������
	 * 	joinColumns={@JoinColumn(name="userId")},joinColumns������ע�⵱ǰʵ�����ڵ��������е��ֶ���
	 *  inverseJoinColumns={@JoinColumn(name="groupId")}	inverseJoinColumns������ע�⵱ǰʵ���������е����ö������м����ֶ���
	 * ) 
	 */
	@ManyToMany
	@JoinTable(name="tb_user_group"
			,joinColumns={@JoinColumn(name="userId")}
			,inverseJoinColumns={@JoinColumn(name="groupId")})
	public Set<Group> getGroups() {
		return groups;
	}
	public void setGroups(Set<Group> groups) {
		this.groups = groups;
	}
}
