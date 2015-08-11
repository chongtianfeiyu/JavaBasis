package com.startcaft.spring.jdbc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

public class JbdcTest {

	private ClassPathXmlApplicationContext context = null;
	private JdbcTemplate jdbcTemplate = null;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate = null;
	private EmployeeDAO empDao = null;

	{
		context = new ClassPathXmlApplicationContext("applicationcontext.xml");
		jdbcTemplate = (JdbcTemplate) context.getBean("jdbTemplate");
		empDao = context.getBean(EmployeeDAO.class);
		namedParameterJdbcTemplate = context.getBean(NamedParameterJdbcTemplate.class);
	}
	
	@Test
	public void employeeDaoTest(){
		Employee emp = empDao.get(1);
		System.out.println(emp);
	}
	
	/**
	 * 为了解决在SQL参数多的时候，造成的麻烦
	 * 可以使用SqlParameterSource接口，并为其实现类BeanPropertySqlParameterSource传递一个实际的对象；
	 * 需要注意：具名参数 要与 实际的对象的属性名一致
	 */
	@Test
	public void namedParameterJdbcTemplateTest2(){
		String sql = "insert into department(depart_name) values(:departName)";
		Department depart = new Department();
		depart.setDepartName("人事部");
		SqlParameterSource paramSource = new BeanPropertySqlParameterSource(depart);
		namedParameterJdbcTemplate.update(sql, paramSource);
	}
	
	/**
	 * 具名参数JdbcTemplate
	 * 就是给参数别名，在SQL参数比较多的时候，不容易混淆。
	 * 缺点就是：稍微麻烦一点
	 */
	@Test
	public void namedParameterJdbcTemplateTest(){
		String sql = "insert into employee(last_name,email,depart_id) values(:ln,:email,:departId)";
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("ln", "XYZ");
		paramMap.put("email", "xyz@163.com");
		paramMap.put("departId", 1);
		namedParameterJdbcTemplate.update(sql, paramMap);
	}
	
	
	/**
	 * 查询实体对象的集合;
	 * 注意不是调用queryForObject()方法
	 * 而是调用query()方法
	 */
	@Test
	public void queryForListTest() {
		String sql = "select id,last_name lastname,email from employee where id > ?";
		RowMapper<Employee> rowMapper = new BeanPropertyRowMapper<Employee>(
				Employee.class);
		List<Employee> list = jdbcTemplate.query(sql, rowMapper, 3);
		System.out.println(list);
	}
	
	/**
	 * 查询单个列的值，或者做统计查询;
	 * 使用queryForObject()方法
	 */
	@Test
	public void queryForObjectTest2(){
		String sql = "select count(id) from employee";
		long count = jdbcTemplate.queryForObject(sql, Long.class);
		System.out.println(count);
	}

	/**
	 * 从数据库中获取一条数据，并得到对应的一个对象
	 */
	@Test
	public void queryForObjectTest() {
		// 需要注意，1，使用SQL的字段别名 完成类的属性名 和 字段名的映射
		// 需要注意，2，jdbcTemplate不能像Hibernate那样设置级联属性。
		String sql = "select id,last_name lastname,email,depart_id as 'department.id' from employee where id = ?";
		// Employee emp = jdbcTemplate.queryForObject(sql, Employee.class,1);

		// 用RowMapper
		RowMapper<Employee> rowMapper = new BeanPropertyRowMapper<Employee>(
				Employee.class);
		Employee emp = jdbcTemplate.queryForObject(sql, rowMapper, 1);
		System.out.println(emp);
	}

	/**
	 * 批量 INSERT UPDATE DELETE语句
	 */
	@Test
	public void batchUpdateTest() {
		String sql = "insert into employee(last_name,email,depart_id) values(?,?,?)";
		List<Object[]> batchArgs = new ArrayList<Object[]>();
		batchArgs.add(new Object[] { "AA", "aa@163.com", 1 });
		batchArgs.add(new Object[] { "BB", "bb@163.com", 1 });
		batchArgs.add(new Object[] { "CC", "cc@163.com", 2 });
		batchArgs.add(new Object[] { "DD", "dd@163.com", 3 });
		batchArgs.add(new Object[] { "EE", "ee@163.com", 2 });
		jdbcTemplate.batchUpdate(sql, batchArgs);
	}

	/**
	 * 执行 INSERT UPDATE DELETE语句
	 */
	@Test
	public void updateTest() {
		String sql = "UPDATE EMPLOYEE set last_name = ? where id = ?";
		jdbcTemplate.update(sql, "zhangsan", 1);
	}

	@Test
	public void testDataSource() {
		DataSource dataSource = (DataSource) context.getBean("dataSource");
		System.out.println(dataSource);
	}

}
