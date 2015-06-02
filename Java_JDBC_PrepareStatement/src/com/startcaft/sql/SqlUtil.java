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

	// ��̬�����,��һ���ؾ������У�ȴִֻ��һ��,������ʼ��������
	static {
		// 1.��ȡ�ֽ������
		Class clazz = SqlUtil.class;
		// 2.����getResourceAsStream��ȡ�����ļ���������
		InputStream inputStream = clazz.getResourceAsStream("/db.properties");
		// 3.ͨ�����������������ļ�����
		Properties properties = new Properties();
		try {
			properties.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("���������ļ�ʧ��");
		}

		driverClassName = properties.getProperty("diverClassName");
		urlString = properties.getProperty("url");
		userString = properties.getProperty("user");
		password = properties.getProperty("password");

		try {
			// 4.ע������
			Class.forName(driverClassName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("ע������ʧ��");
		}
	}

	/***
	 * ����һ�����ݿ�����
	 * 
	 * @return
	 */
	public static Connection getConnection() {
		Connection conn = null;
		try {
			// 5.��ȡ����
			conn = DriverManager.getConnection(urlString, userString, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//�������Ӷ���
		return conn;
	}
	
	
	/***
	 * �ر�SQL��������Դ
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
