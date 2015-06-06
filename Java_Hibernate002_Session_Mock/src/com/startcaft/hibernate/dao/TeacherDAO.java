package com.startcaft.hibernate.dao;

import com.startcaft.hibernate.model.Teacher;

public class TeacherDAO {
	
	public void Insert() throws Exception{
		
		Teacher teacher = new Teacher();
		teacher.setId(1001);
		teacher.setName("noname");
		teacher.setAge(50);
		
		Session session = new Session();
		session.save(teacher);
		
		System.out.println("ok");
	}
}
