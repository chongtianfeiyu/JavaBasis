package com.startcaft.spring.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository()
public class EmployeeDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public Employee get(Integer id){
		String sql = "select id,last_name lastname,email from employee where id = ?";
		RowMapper<Employee> rowMapper = new BeanPropertyRowMapper<Employee>(
				Employee.class);
		Employee emp = jdbcTemplate.queryForObject(sql, rowMapper, id);
		return emp;
	} 
}
