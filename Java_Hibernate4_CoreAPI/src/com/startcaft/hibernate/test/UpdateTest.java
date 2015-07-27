package com.startcaft.hibernate.test;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.startcaft.hibernate.entity.Teacher;
import com.startcaft.hibernate.util.HibernateUtil;

public class UpdateTest {

	private static SessionFactory sessionFactory;

	@BeforeClass
	public static void initSessionFactory() {
		sessionFactory = HibernateUtil.getSessionFactory();
	}
	
	@AfterClass
	public static void free() {
		sessionFactory.close();
	}
	
	/*
	 * update ���£�
	 * 1���������� detached ���󣬸�����ɺ�״̬Ϊpersistent��
	 * 2������ transient ����ᱨ��
	 * 3����������Լ��趨��oid��transient�������(ǰ�������ݿ����ж�Ӧ�ļ�¼)��
	 * 4��persitent ״̬�µĶ���ֻҪ�޸Ķ�������ԣ������ύ�ͻ��Զ�����update��䡣
	 * 5�����²��ָ��ĵ��ֶΣ�
	 * ----@Column(updatable=false),�����������ԣ����������ֶβ�����update�����������Ƽ�ʹ�á�
	 * ----��ʵ�������ļ�.hbm.xml��class�ڵ�������dynamic-update="true";
	 * 	       �༶ע��@DynamicUpdate(true);����jpa�Ķ��壬��Hibernate��ע�����չ��
	 * 
	 * ----���Ƽ�ʹ��HQL��䡣��
	 * 
	 * 6����session���µ��������Ҫ����������û�ж���ɱȡ�����ʹ��merge()���������˽⼴�ɡ�
	 */
	@Test
	public void testUpdate1(){
		
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		Teacher t = (Teacher) session.get(Teacher.class, 1);
		
		session.getTransaction().commit();
		
		t.setName("teacher_name");//�й�״̬�ˡ�
		
		
		Session session2 = sessionFactory.getCurrentSession();
		session2.beginTransaction();
		session2.update(t);
		
		session2.getTransaction().commit();
	}
	
	@Test
	public void testUpdate2(){
		
		Teacher t = new Teacher();
		t.setName("zhangsan");//transient ״̬ ȴû������id���޷����¡�
		
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.update(t);
		session.getTransaction().commit();
	}
	
	@Test
	public void testUpdate3(){
		
		Teacher t = new Teacher();
		t.setId(1);
		t.setName("startcaft");//transient ״̬  �ֶ�����id�����ݿ��ж�Ӧ���ݣ����Ը��¡�
		
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.update(t);
		session.getTransaction().commit();
	}
	
	@Test
	public void testUpdate4(){
		
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		Teacher t = (Teacher) session.get(Teacher.class, 1);//�־û� ״̬,���������ݿ�Ҫͬ����
		
		t.setName("zz4");
		
		session.getTransaction().commit();
	}
	
	@Test
	public void testUpdate5(){
		
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		Teacher t = (Teacher) session.get(Teacher.class, 1);//�־û� ״̬,���������ݿ�Ҫͬ����
		t.setName("z4");//���ָ��£��������еĶ�����бȽϡ���˭ ����˭��
		session.getTransaction().commit();
		
		t.setName("bwm");//detached ����
		
		Session session2 = sessionFactory.getCurrentSession();
		session2.beginTransaction();
		session2.update(t);//ȫ���޸ģ���Ϊ������û�ж���û�ñȽϣ�����ȫ�����¡�
		session2.getTransaction().commit();
	}
	
	@Test
	public void testUpdate6(){
		
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		Teacher t = (Teacher) session.get(Teacher.class, 1);//�־û� ״̬,���������ݿ�Ҫͬ����
		t.setName("z4");//���ָ��£��������еĶ�����бȽϡ���˭ ����˭��
		session.getTransaction().commit();
		
		t.setName("bwm");//detached ����
		
		Session session2 = sessionFactory.getCurrentSession();
		session2.beginTransaction();
		session2.merge(t);//ֻ����һ���ˡ�����update֮ǰ����һ��select��䡾���������ڱȽϣ���Ϊ������û�ж�Ӧ�Ķ��󡣡�
		session2.getTransaction().commit();
	}
	
	@Test
	public void testUpdate7(){
		
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		Query query =session.createQuery("update Teacher t set t.name='z5' where id=1");
		query.executeUpdate();
	}
}
