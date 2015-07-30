package com.startcaft.hibernate.test;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.startcaft.hibernate.entity.Group;
import com.startcaft.hibernate.entity.User;
import com.startcaft.hibernate.util.HibernateUtil;

/*
 * Cascade�������Ϊ�˼��ٴ���������û��̫������塣
 * 
 * �����ܽ᣺
 * 1�����ݿ��е�˫���ϵ�������ڳ���������˫�����
 * 2��˫������У��趨��mappedBy��
 */
public class CascadeTest {

	private static SessionFactory sessionFactory;

	@BeforeClass
	public static void initSessionFactory() {
		sessionFactory = HibernateUtil.getSessionFactory();
	}

	@AfterClass
	public static void free() {
		sessionFactory.close();
	}

	@Test
	public void createTable() {
		new SchemaExport(new Configuration().configure()).create(true, true);
	}

	/**
	 * �ѹ���������ʵ����󣬵�����������������save�����Ա��浫��û�����������
	 */
	@Test
	public void saveAsTwoEntityObject() {
		User user = new User();
		user.setName("����");

		Group group = new Group();
		group.setName("����Ա");

		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();

		session.save(group);
		session.save(user);

		tx.commit();
	}

	/**
	 * �ѹ���������ʵ����󣬵�����������������save,����������������֮��Ĺ�����ϵ��OKû���⡣
	 * ���ǣ���Ȼ���й�����ϵ����������ΪʲôҪsave�����أ�
	 */
	@Test
	public void saveNoCascade() {
		User user = new User();
		user.setName("����");

		Group group = new Group();
		group.setName("����Ա");

		user.setGroup(group);// ���ù�������

		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();

		session.save(group);
		session.save(user);

		tx.commit();
	}

	/**
	 * ��ManyToOne���Cascade����;
	 * ֻ����User�����һ���������������Group��һ��һ����Ҳһ�������棬��Ȼ��ҲҪ�������ǵĹ�����ϵ
	 */
	@Test
	public void saveCascadeByMany() {
		User user = new User();
		user.setName("����");

		Group group = new Group();
		group.setName("����Ա");

		user.setGroup(group);// ���ù�������

		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();

		// User�������������Group����һ�����档
		session.save(user);

		tx.commit();
	}

	/**
	 * ��OneToMany���Cascade����;
	 * ֻ����Group��һ��һ���������������Users�����һ����Ҳһ�������棬��Ȼ��ҲҪ�������ǵĹ�����ϵ��
	 * 
	 * ������ һ��һ������ �Ƚ��鷳��
	 */
	@Test
	public void saveCascadeByOne() {
		User user = new User();
		user.setName("����");

		User user2 = new User();
		user2.setName("����");

		User user3 = new User();
		user3.setName("����");

		Group group = new Group();
		group.setName("����Ա");

		// ��һ��һ���ĽǶ����ù�����ϵ
		Set<User> users = new HashSet<User>();
		users.add(user);
		users.add(user2);
		users.add(user3);
		group.setUsers(users);

		// վ��Hibernate�ĽǶȣ��ڱ���User��ʱ�򣬱���֪��Group�����á�
		// ���ԣ�Hibernate��һ���ص���ǣ��� ���һ�� �����Ƚϼ򵥣��� һ��һ�� �����Ƚϸ��ӡ�
		user.setGroup(group);
		user2.setGroup(group);
		user3.setGroup(group);

		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();

		// User�������������Group����һ�����档
		session.save(group);

		tx.commit();
	}
}
