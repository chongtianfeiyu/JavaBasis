package com.hibernate.dao.test;

import static org.junit.Assert.*;

import org.hamcrest.Matchers;
import org.junit.Test;

import com.startcaft.dao.TeacherDAO;

public class TeacherDAOTest {

	@Test
	public void testInsert() {
		
		boolean result = new TeacherDAO().Insert();
		assertThat(result, Matchers.is(true));
	}

}
