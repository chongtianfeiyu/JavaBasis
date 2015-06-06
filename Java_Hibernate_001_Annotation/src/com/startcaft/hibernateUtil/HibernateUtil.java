package com.startcaft.hibernateUtil;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.classic.Session;


/**
 * Hibernate的SeesinFactory工具类，
 * 一个应用程序只需要生成一个SessionFactory对象即可，单例。
 * 
 * @author startcaft
 *
 */
public class HibernateUtil {
	
	private static final SessionFactory SESSION_FACTORY;
	
	//静态代码块,用于初始化SESSION_FACTORY
	static{
		try {
			//这是使用.hbm.xml映射文件时候的创建SessionFactory的方法
			//SESSION_FACTORY = new Configuration().buildSessionFactory();
			
			//使用注解的方式映射实体对象，创建SessionFactory使用AnnotationConfiguration类。
			SESSION_FACTORY = new AnnotationConfiguration().configure().buildSessionFactory();
		} catch (Throwable e) {
			
			throw new ExceptionInInitializerError(e);
		}
	}
	
	
	/**
	 * 通过SessionFactory返回一个Session
	 * @return org.hibernate.Session
	 * @throws HibernateException
	 */
	public static Session getSession() throws HibernateException {
		
		return SESSION_FACTORY.openSession();
	}
}
