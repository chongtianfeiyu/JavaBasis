package com.startcaft.sql;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;

public class SqlUtil {

	private static String driverClassName = null;
	private static String urlString = null;
	private static String userString = null;
	private static String password = null;

	// 静态代码块,类一加载就自运行，却只执行一次,用来初始化这个类的
	static {
		// 1.获取字节码对象
		Class clazz = SqlUtil.class;
		// 2.调用getResourceAsStream获取配置文件的输入流
		InputStream inputStream = clazz.getResourceAsStream("/db.properties");
		// 3.通过输入流加载配置文件对象
		Properties properties = new Properties();
		try {
			properties.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("加载配置文件失败");
		}

		driverClassName = properties.getProperty("diverClassName");
		urlString = properties.getProperty("url");
		userString = properties.getProperty("user");
		password = properties.getProperty("password");

		try {
			// 4.注册驱动
			Class.forName(driverClassName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("注册驱动失败");
		}
	}

	/***
	 * 返回一个数据库连接
	 * 
	 * @return
	 */
	public static Connection getConnection() {
		Connection conn = null;
		try {
			// 5.获取连接
			conn = DriverManager.getConnection(urlString, userString, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//返回连接对象
		return conn;
	}
	
	
	/***
	 * 关闭SQL的连接资源
	 * 
	 * @param statement
	 * @param connection
	 */
	public static void close(Statement statement, Connection connection) {
		try {
			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				connection.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
