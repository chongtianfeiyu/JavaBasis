package com.startcaft.hibernate.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 因为树型节点所有的数据，在数据库中只是存储在一个表中，
 * 而对于实体来说，节点对于子节点来说是 一对多 的关系，
 * 而对于父节点来说是 多对一 的关系。
 * @author Administrator
 */
@Entity
@Table(name="tb_node")
public class Node {
	private int id;//标识符
	private String name;//节点名称
	private int level;//层次，为了输出设计
	private boolean leaf;//是否为叶子节点，为了效率设计，可有可无
	
	//父节点:因为多个节点属于一个父节点，因此用Hibernate映射关系就是"多对一"
	private Node parent;
	
	//子节点：因为一个节点可以有多个子节点，因此用Hibernate映射关系就是"一对多"
	private Set<Node> children = new HashSet<Node>();
	
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public boolean isLeaf() {
		return leaf;
	}

	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}
	
	@ManyToOne
	@JoinColumn(name="parent_id")
	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	@OneToMany(mappedBy="parent")
	public Set<Node> getChildren() {
		return children;
	}

	public void setChildren(Set<Node> children) {
		this.children = children;
	}
}
