package com.startcaft.hibernate.entity;


import java.util.Date;

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
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name="Tb_Teacher")
@DynamicUpdate(true)
public class Teacher {
	
	private int id;
	private String name;
	private String title;
	private String myWife;
	private Date createTime;
	private ZhiCheng zhiCheng;
	
	//XML映射Enum枚举很麻烦，Annotation很便捷。
	//EnumType.STRING表示保存Enum 的项 的字符串表现形式，
	//EnumType.ORDINAL表示保存Enum 的项 的原始值,从0开始
	@Enumerated(EnumType.ORDINAL)
	public ZhiCheng getZhiCheng() {
		return zhiCheng;
	}
	public void setZhiCheng(ZhiCheng zhiCheng) {
		this.zhiCheng = zhiCheng;
	}
	
	//时间格式，默认映射成日期+时间 :@Temporal(TemporalType.TIMESTAMP)
	@Temporal(TemporalType.DATE)
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	//不进行映射,默认是@Basic,在XML中不写就行了。
	@Transient
	public String getMyWife() {
		return myWife;
	}
	public void setMyWife(String myWife) {
		this.myWife = myWife;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name="t_name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="t_title",updatable=false)
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Override
	public String toString() {
		return "Teacher [id=" + id + ", name=" + name + ", title=" + title
				+ "]";
	}
}
