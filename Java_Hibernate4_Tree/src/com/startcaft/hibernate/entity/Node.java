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
 * ��Ϊ���ͽڵ����е����ݣ������ݿ���ֻ�Ǵ洢��һ�����У�
 * ������ʵ����˵���ڵ�����ӽڵ���˵�� һ�Զ� �Ĺ�ϵ��
 * �����ڸ��ڵ���˵�� ���һ �Ĺ�ϵ��
 * @author Administrator
 */
@Entity
@Table(name="tb_node")
public class Node {
	private int id;//��ʶ��
	private String name;//�ڵ�����
	private int level;//��Σ�Ϊ��������
	private boolean leaf;//�Ƿ�ΪҶ�ӽڵ㣬Ϊ��Ч����ƣ����п���
	
	//���ڵ�:��Ϊ����ڵ�����һ�����ڵ㣬�����Hibernateӳ���ϵ����"���һ"
	private Node parent;
	
	//�ӽڵ㣺��Ϊһ���ڵ�����ж���ӽڵ㣬�����Hibernateӳ���ϵ����"һ�Զ�"
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
