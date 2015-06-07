package com.startcaft.dao;


import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.classic.Session;

import com.hibernate.model.Sex;
import com.hibernate.model.Student;
import com.startcaft.hibernateUtil.HibernateUtil;

public class StudentDAO {
	
	public boolean InsertStudent(){
		Student s = new Student();
		s.setName("startcaft");
		s.setBirthday(new Date());
		s.setMyWifeName("noname");
		s.setSex(Sex.M);
		
		try {
			Session session = HibernateUtil.getSession();
			session.beginTransaction();
			session.save(s);
			session.getTransaction().commit();
			session.close();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return true;
	}
}
