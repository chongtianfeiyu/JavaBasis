package com.startcaft.hibernate.dao;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.Map;

import com.startcaft.hibernate.model.Teacher;

public class Session {

	private String tableName = "Tb_Teacher";// 数据库表明

	Map<String, String> cfs = new HashMap<String, String>();// 字段名与数据库字段名的映射
	String[] methods;// 存储属性getXXX方法名的数组,getId,getAge,getName

	public Session() {
		cfs.put("id", "id");
		cfs.put("age", "age");
		cfs.put("username", "name");

		methods = new String[cfs.size()];
	}

	public void save(Teacher t) throws Exception {
		
		String sql = this.createSql();
		// 加载驱动
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		// 连接数据库
		Connection connection = DriverManager.getConnection(
				"jdbc:sqlserver://127.0.0.1:1433;DatabaseName=HibernateDB",
				"sa", "5904395");
		// 创建PreparedStatement对象，预编译SQL
		PreparedStatement pStatement = connection.prepareStatement(sql);
		// 给占位符设置值
		for (int i = 0; i< methods.length;i++) {
			Method m = t.getClass().getMethod(methods[i]);//根据方法名获取一个Method对象
			Class returnType = m.getReturnType();//根据Method对象获取它的方法返回值类型
			if (returnType.getName().equals("java.lang.String")) {
				//值得方法并返回值
				String rValue = (String)m.invoke(t);
				pStatement.setString(i + 1,rValue);
			}
			if (returnType.getName().equals("int")) {
				//值得方法并返回值
				Integer rValue = (Integer)m.invoke(t);
				pStatement.setInt(i + 1, rValue);
			}
		}
		// 发送SQL语句
		pStatement.executeUpdate();
		// 销毁资源
		pStatement.close();
		connection.close();
	}

	private String createSql() {

		String columnString = "";// 拼接成id,age,username
		String valueString = "";// 拼接成占位符?,?,?

		int index = 0;
		for (String kString : cfs.keySet()) {
			columnString += kString + ",";
			String vString = cfs.get(kString);
			vString = "get" + Character.toUpperCase(vString.charAt(0))
					+ vString.substring(1);
			methods[index] = vString;

			index++;
		}
		columnString = columnString.substring(0, columnString.length() - 1);

		for (String kString : cfs.keySet()) {
			valueString += "?,";
		}
		valueString = valueString.substring(0, valueString.length() - 1);

		String sql = "insert into " + tableName + " (" + columnString + ")"
				+ " values(" + valueString + ")";
		System.out.println(sql);
		return sql;
	}
}
