package com.startcaft.hibernate.test;

import java.io.File;
import java.util.Iterator;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.startcaft.hibernate.entity.Node;
import com.startcaft.hibernate.util.HibernateUtil;

public class NodeManager {
	
	private static NodeManager nodeManager = new NodeManager();
	
	private NodeManager(){}//因为要使用单例，所以将其构造方法私有化
	
	//向往提供一个接口
	public static NodeManager getInstandce(){
		return nodeManager;
	}
	
	public void saveNode(File file,Session session,Node parent,int level){
		if(file == null || file.exists()){
			return;
		}
		//如果是文件则返回true，则表示是叶子节点，否则为目录，非叶子节点。
		boolean isLeaf = file.isFile();
		Node node = new Node();
		node.setName(file.getName());
		node.setLeaf(isLeaf);
		node.setLevel(level);
		node.setParent(parent);
		
		session.save(node);
		
		//进行循环迭代子目录
		File[] subFiles = file.listFiles();
		if(subFiles != null && subFiles.length > 0){
			for(int i=0;i<subFiles.length;i++){
				this.saveNode(subFiles[i], session, node, level + 1);
			}
		}
	}
	
	public void createNode(String dir){
		Session session = null;
		Transaction tx = null;
		SessionFactory sessionFactory = null;
		try{
			sessionFactory = HibernateUtil.getSessionFactory();
			session  = sessionFactory.getCurrentSession();
			tx = session.beginTransaction();
			
			File root = new File(dir);
			//因为第一个节点无父节点，
			this.saveNode(root, session, null, 0);
			
			tx.commit();
		}
		catch(HibernateException e){
			e.printStackTrace();
			tx.rollback();
		}
		finally{
			sessionFactory.close();
		}
	}
	
	public void printNodeById(int id){
		Session session = null;
		Transaction tx = null;
		SessionFactory sessionFactory = null;
		try{
			sessionFactory = HibernateUtil.getSessionFactory();
			session  = sessionFactory.getCurrentSession();
			tx = session.beginTransaction();
			
			Node node = (Node) session.get(Node.class, 1);
			
			printNode(node);
			
			tx.commit();
		}
		catch(HibernateException e){
			e.printStackTrace();
			tx.rollback();
		}
		finally{
			sessionFactory.close();
		}
	}

	private void printNode(Node node) {
		if(node == null){return;}
		int level = node.getLevel();
		if(level > 0){
			for(int i=0;i<level;i++){
				System.out.println(" |");
			}
			System.out.println("--");
		}
		System.out.println(node.getName() + (node.isLeaf() ? "" : "[" + node.getChildren().size() + "]"));
		Set<Node> children = node.getChildren();
		for(Iterator<Node> it = children.iterator();it.hasNext();){
			Node child = it.next();
			printNode(child);
		}
	}
}
