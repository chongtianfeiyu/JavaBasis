package com.startcaft.hibernate.test;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.startcaft.hibernate.entity.Category;
import com.startcaft.hibernate.entity.Msg;
import com.startcaft.hibernate.entity.MsgInfo;
import com.startcaft.hibernate.entity.Topic;
import com.startcaft.hibernate.util.HibernateUtil;

/*
 * Hibernate��ѯ (Query Language)
 * Hibernate�п���ʹ�õļ��ֲ�ѯ���
 * ----1��NativeSQL����������(���ݿ��Լ���SQL���)
 * ----2��HQL��Hibernate�Դ��Ĳ�ѯ��䣬����ʹ��HQL��䣬ת���ɾ���ķ���
 * ----3��EJBQL��JPQL1.0��������Ϊ����HQL��һ���ӽڡ��ص㡿
 * ----4��QBC��Query by Cretira
 * ----5��QBE��Query by Example
 * 
 * ���ϵķ�����1��5�ıȽϣ�1�Ĺ������5�Ĺ�����С��
 */
public class QueryLanguageTest {

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

	@Test
	public void testSave() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		for (int i = 0; i < 10; i++) {
			Category c = new Category();
			c.setName("c" + i);
			session.save(c);
		}

		for (int i = 0; i < 10; i++) {
			Category c = new Category();
			c.setId(1);

			Topic t = new Topic();
			t.setCategory(c);
			t.setTilte("t" + i);
			t.setCreateDate(new Date());
			session.save(t);
		}

		for (int i = 0; i < 10; i++) {
			Topic t = new Topic();
			t.setId(1);

			Msg m = new Msg();
			m.setCont("m" + i);
			m.setTopic(t);
			session.save(m);
		}

		session.getTransaction().commit();
		session.close();
	}

	@Test
	public void queryTest1() {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		/** QL:from + ʵ������ */
		Query query = session.createQuery("from Category");
		List<Category> categories = query.list();
		for (Category c : categories) {
			System.out.println(c.getName());
		}
		session.getTransaction().commit();
	}

	@Test
	public void queryTest2() {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		/** Ҳ����Ϊʵ�������������Ȼ��ʹ�ñ��� */
		Query query = session
				.createQuery("from Category c where c.name > 'c5'");
		List<Category> categories = query.list();
		for (Category c : categories) {
			System.out.println(c.getName());
		}
		session.getTransaction().commit();
	}

	@Test
	public void queryOrderByTest() {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		/** ���� */
		Query query = session
				.createQuery("from Category c order by c.name desc");
		List<Category> categories = (List<Category>) query.list();

		for (Category c : categories) {
			System.out.println(c.getName());
		}

		session.getTransaction().commit();
	}

	/** ��������ѯ */
	@Test
	public void queryTest3() {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		/** ���� */
		Query query = session
				.createQuery("select c from Category c order by c.name desc");
		List<Category> categories = (List<Category>) query.list();

		for (Category c : categories) {
			System.out.println(c.getName());
		}

		/** Ϊ���������Ķ�������������������Լ���ʹ�� */
		/** ����ʹ��ð��(:)����Ϊռλ���������ܲ���ʹ�á�����(��ʽ���) */
		/*
		 * Query q =
		 * session.createQuery("from Category c where c.id > :minand c.id < :max"
		 * ); //q.setParameter("min",2); //q.setParameter("max",8);
		 * q.setInteger("min",2); q.setInteger("max",8);
		 */

		Query q2 = session
				.createQuery(
						"from Category c where c.id > :min and c.id < :max")
				.setInteger("min", 2).setInteger("max", 8);

		List<Category> cs = (List<Category>) q2.list();
		for (Category c : cs) {
			System.out.println(c.getId() + "-" + c.getName());
		}

		/** Ҳ����ʹ�ô�ͳ��ռλ��(?)�������ܲ���ʹ�� */
		Query q3 = session
				.createQuery("from Category c where c.id > ? and c.id < ?");
		q3.setParameter(0, 2);
		q3.setParameter(1, 8);

		List<Category> css = (List<Category>) q3.list();
		for (Category c : css) {
			System.out.println(c.getId() + "-" + c.getName());
		}

		session.getTransaction().commit();
	}

	/** ��ҳ��ѯ */
	@Test
	public void queryPageTest() {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();

		Query query = session
				.createQuery("from Category c order by c.name desc");
		query.setMaxResults(4);// ����ÿҳ��ʾ������¼��
		query.setFirstResult(0);// ���ôӵڼ�����ʼ����0��ʼ����

		List<Category> categoires = query.list();

		for (Category c : categoires) {
			System.out.println(c.getId() + "-" + c.getName());
		}

		session.getTransaction().commit();
	}

	/** ��ѯ���صĲ���һ��ʵ����� */
	@Test
	public void queryTest4() {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();

		Query query = session
				.createQuery("select c.id,c.name from Category c order by c.name desc");

		List<Object[]> categoires = (List<Object[]>) query.list();

		for (Object[] obj : categoires) {
			System.out.println(obj[0] + "-" + obj[1]);
		}

		session.getTransaction().commit();
	}

	/** ������ѯ��ע��FecthType */
	@Test
	public void relationshipQueryTest() {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();

		// ��ѯcategory_id=1��Topic������
		Query query = session
				.createQuery("from Topic t where t.category.id = 1");
		List<Topic> topcis = (List<Topic>) query.list();

		// FectTypeΪLAZY
		for (Topic t : topcis) {
			System.out.println(t.getTilte());

			// ���͵ڶ������
			System.out.println(t.getCategory().getName());
		}
	}

	/** ��ѯһ��ʵ���࣬����һ��VO�����װ֮��Ҫ��VO��һ����Ӧ�Ĺ��캯���� */
	@Test
	public void queryByDTOTest() {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();

		Query query = session
				.createQuery("select new com.startcaft.hibernate.entity.MsgInfo(m.id,m.cont,m.topic.tilte,m.topic.category.name) from Msg m");

		for (Object o : query.list()) {
			MsgInfo msg = (MsgInfo) o;
			System.out.println(msg.getCont());
		}
	}

	/** �ֶ�ʹ�� left right join��������ѯ */
	@Test
	public void queryJoinTest() {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();

		// ����Ϊʲô����ֱ��дCategory��������дt.category
		// ��Ϊͬһ�������п��ܴ��ڶ����Ա��������Ҫ��ȷָ������һ����Ա���������������������ӡ�
		Query query = session
				.createQuery("select c.name,t.id from Topic t join t.category c");// join
																					// Category
																					// c
		// List<Topic> topcis = (List<Topic>)query.list();
		//
		// for(Topic t : topcis){
		// System.out.println(t.getTilte());
		// //System.out.println(t.getCategory().getName());�����ڶ�����䡣
		// }

		for (Object o : query.list()) {
			Object[] fileds = (Object[]) o;
			System.out.println(fileds[0] + "-" + fileds[1]);
		}
	}

	/** Query��list�������ض��ʵ������null��Query��uniqueResult��������һ��ʵ������null */
	@Test
	public void queryTest5() {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();

		Query query = session
				.createQuery("select max(m.id),min(m.id),avg(m.id),sum(m.id) from Msg m");
		Object[] o = (Object[]) query.uniqueResult();

		System.out.println(o[0] + "-" + o[1] + "-" + o[2] + "-" + o[3]);
	}

	/** between ��ѯ */
	@Test
	public void queryTest6() {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();

		Query query = session
				.createQuery("from Msg m where m.id between 3 and 5");
		for (Object o : query.list()) {
			Msg m = (Msg) o;
			System.out.println(m.getId() + "-" + m.getCont());
		}
	}
	
	/** in ��ѯ */
	@Test
	public void queryTest7(){
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();

		Query query = session
				.createQuery("from Msg m where m.id in (3,4,5)");
		for (Object o : query.list()) {
			Msg m = (Msg) o;
			System.out.println(m.getId() + "-" + m.getCont());
		}
	}
	
	
	/** is null �� is not null ��ѯ */
	@Test
	public void queryTest8(){
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		
		Query query = session
				.createQuery("from Msg m where m.cont is not null");
		for (Object o : query.list()) {
			Msg m = (Msg) o;
			System.out.println(m.getId() + "-" + m.getCont());
		}
	}
	
	
}
