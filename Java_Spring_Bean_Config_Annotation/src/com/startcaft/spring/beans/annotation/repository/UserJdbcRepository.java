package com.startcaft.spring.beans.annotation.repository;

import org.springframework.stereotype.Repository;

@Repository("userJdbcRepository")
public class UserJdbcRepository implements UserRepository {

	@Override
	public void save() {
		System.out.println("UserJdbcRepository save...");
	}
}
