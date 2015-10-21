package com.startcaft.m2ssh.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="TB_DEPART")
public class Department {
	
	public Integer id;
	private Integer pId = 0;							//部门父节点ID，默认为0，也就是顶层节点
	private String name;								
	private boolean isEnable = true;					//是否可用，默认为true
	private Date createTime = new Date();				//创建时间，默认为系统当前时间
	private String desc;
	private Set<User> users = new HashSet<User>();		//部门包含的用户
	
	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name="parent_id",nullable=false)
	public Integer getpId() {
		return pId;
	}
	public void setpId(Integer pId) {
		this.pId = pId;
	}
	
	@Column(name="depart_name",length=50,unique=true,nullable=false)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="is_enable",nullable=false)
	public boolean isEnable() {
		return isEnable;
	}
	public void setEnable(boolean isEnable) {
		this.isEnable = isEnable;
	}
	
	@Column(name="create_time",nullable=false)
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@Column(name="depart_desc",nullable=true)
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	@OneToMany(mappedBy="department")
	public Set<User> getUsers() {
		return users;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
	}
}
