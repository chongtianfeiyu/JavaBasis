package com.startcaft.sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestDQL {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn = SqlUtil.getConnection();
		
		Statement statement = conn.createStatement();
		//׼��SQL���
		String sqlString = "select * from myTable";
		
		//����executeQuery������һ�������ResultSet
		ResultSet rSet = statement.executeQuery(sqlString);
		
		while(rSet.next()){
			
			//��ȡ��ѯ����е��ֶε�ֵ��һ��Ҫ��ȷ�ֶ����ƺ��ֶ�����
			int userId = rSet.getInt("userId");
			String userName = rSet.getString("userName");
			System.out.println("userId:" + userId + "--->userName:" + userName);
		}
		
		SqlUtil.close(statement, conn);
	}

}
