package com.startcaft.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/***
 * Java.sql.PreparedStatement��Statement���ӽӿڡ�
 * Statement����ִ�о�̬��SQL��䣬��PreparedStatement����ִ��Ԥ�����SQL
 * 
 * PreparedStatement�����Statement��˵����ȫ�Ը���(��ֹSQLע��)��Ч�ʸ��ߡ�
 * 
 * @author wow
 *
 */
public class TestPreparedStatement {
	
	public static void main(String[] args) {

		//1.��ȡ���Ӷ���
		Connection con = SqlUtil.getConnection();
		
		//2.Ԥ�����SQL��䣬������?ռλ
		String insertString = "insert into myTable(userid,username) values(?,?)";
		
		PreparedStatement preparedStatement = null;
			
		try {
			//3.ʹ��Ԥ�����SQL���������һ��PreparedStatement����
			preparedStatement = con.prepareStatement(insertString);
			//4.����SQL����еĲ�����Ҫ��ȷ��������,ע������������Ǵ�1��ʼ�İ�
			preparedStatement.setInt(1, 444);
			preparedStatement.setString(2, "lvmengjie");
			//5.����SQL���
			int result = preparedStatement.executeUpdate();
			System.out.println("��" + result + "����¼��Ӱ��");
			SqlUtil.close(preparedStatement, con);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//�����´�ӡһ�����е�����
		printTable();
	}
	
	
	private static void printTable(){
		Connection con = SqlUtil.getConnection();
		try {
			Statement statement = con.createStatement();
			String sql = "select * from myTable";
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				int userId = rs.getInt(1);
				String userName = rs.getString(2);
				System.out.println("userId:" + userId + "--->>>userName:" + userName);
			}
			SqlUtil.close(statement, con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
