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
 * Hibernate查询 (Query Language)
 * Hibernate中可以使用的几种查询语句
 * ----1，NativeSQL：本地语言(数据库自己的SQL语句)
 * ----2，HQL：Hibernate自带的查询语句，可以使用HQL语句，转换成具体的方言
 * ----3，EJBQL：JPQL1.0，可以认为它是HQL的一个子节【重点】
 * ----4，QBC：Query by Cretira
 * ----5，QBE：Query by Example
 * 
 * 以上的方法从1到5的比较，1的功能最大，5的功能最小。
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
		/** QL:from + 实体类名 */
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
		/** 也可以为实体类起个别名，然后使用别名 */
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
		/** 排序 */
		Query query = session
				.createQuery("from Category c order by c.name desc");
		List<Category> categories = (List<Category>) query.list();

		for (Category c : categories) {
			System.out.println(c.getName());
		}

		session.getTransaction().commit();
	}

	/** 参数化查询 */
	@Test
	public void queryTest3() {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		/** 排序 */
		Query query = session
				.createQuery("select c from Category c order by c.name desc");
		List<Category> categories = (List<Category>) query.list();

		for (Category c : categories) {
			System.out.println(c.getName());
		}

		/** 为加载上来的对象属性起别名，还可以继续使用 */
		/** 可以使用冒号(:)，作为占位符，来接受参数使用。如下(链式编程) */
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

		/** 也可以使用传统的占位符(?)，来接受参数使用 */
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

	/** 分页查询 */
	@Test
	public void queryPageTest() {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();

		Query query = session
				.createQuery("from Category c order by c.name desc");
		query.setMaxResults(4);// 设置每页显示的最大记录数
		query.setFirstResult(0);// 设置从第几条开始，从0开始算起

		List<Category> categoires = query.list();

		for (Category c : categoires) {
			System.out.println(c.getId() + "-" + c.getName());
		}

		session.getTransaction().commit();
	}

	/** 查询返回的不是一个实体对象 */
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

	/** 关联查询，注意FecthType */
	@Test
	public void relationshipQueryTest() {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();

		// 查询category_id=1的Topic数据项
		Query query = session
				.createQuery("from Topic t where t.category.id = 1");
		List<Topic> topcis = (List<Topic>) query.list();

		// FectType为LAZY
		for (Topic t : topcis) {
			System.out.println(t.getTilte());

			// 发送第二条语句
			System.out.println(t.getCategory().getName());
		}
	}

	/** 查询一个实体类，并将一个VO对象封装之【要求VO有一个对应的构造函数】 */
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

	/** 手动使用 left right join来关联查询 */
	@Test
	public void queryJoinTest() {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();

		// 这里为什么不能直接写Category，而必须写t.category
		// 因为同一个类中有可能存在多个成员变量，需要明确指定用哪一个成员变量的连接条件来做连接。
		Query query = session
				.createQuery("select c.name,t.id from Topic t join t.category c");// join
																					// Category
																					// c
		// List<Topic> topcis = (List<Topic>)query.list();
		//
		// for(Topic t : topcis){
		// System.out.println(t.getTilte());
		// //System.out.println(t.getCategory().getName());发出第二条语句。
		// }

		for (Object o : query.list()) {
			Object[] fileds = (Object[]) o;
			System.out.println(fileds[0] + "-" + fileds[1]);
		}
	}

	/** Query的list方法返回多个实例或者null，Query的uniqueResult方法返回一个实例或者null */
	@Test
	public void queryTest5() {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();

		Query query = session
				.createQuery("select max(m.id),min(m.id),avg(m.id),sum(m.id) from Msg m");
		Object[] o = (Object[]) query.uniqueResult();

		System.out.println(o[0] + "-" + o[1] + "-" + o[2] + "-" + o[3]);
	}

	/** between 查询 */
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
	
	/** in 查询 */
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
	
	
	/** is null 与 is not null 查询 */
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
