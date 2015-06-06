package com.startcaft.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.hibernate.model.Teacher;
import com.startcaft.hibernateUtil.HibernateUtil;

public class TeacherDAO {
	
	public boolean Insert(){
		Teacher teacher = new Teacher();
		teacher.setId(001);
		teacher.setAge(40);
		teacher.setName("t1");
		
		try {
			Session session = HibernateUtil.getSession();
			//提交一组事物，养成这样一个好习惯
			session.beginTransaction();
			session.save(teacher);
			session.getTransaction().commit();
			return true;
			
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		
		return false;
	}
}
