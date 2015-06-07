package com.hibernate.dao.test;

import static org.junit.Assert.*;

import org.hamcrest.Matchers;
import org.junit.Test;

import com.startcaft.dao.StudentDAO;

public class StudentDAOTest {

	@Test
	public void testInsertStudent() {
		boolean result = new StudentDAO().InsertStudent();
		assertThat(result, Matchers.is(true));
	}

}
