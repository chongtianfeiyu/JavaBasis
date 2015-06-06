package com.startcaft.junit.test;

import static org.junit.Assert.*;

import org.hamcrest.Matchers;
import org.junit.Test;

import com.startcaft.junit.User;

public class UserTest {

	@Test
	public void testGetName() {
		
		assertThat(new User().getName(), Matchers.is("宋慧乔"));
	}

}
