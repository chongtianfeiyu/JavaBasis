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
		//准备SQL语句
		String sqlString = "select * from myTable";
		
		//调用executeQuery并返回一个结果集ResultSet
		ResultSet rSet = statement.executeQuery(sqlString);
		
		while(rSet.next()){
			
			//获取查询结果中的字段的值，一定要明确字段名称和字段类型
			int userId = rSet.getInt("userId");
			String userName = rSet.getString("userName");
			System.out.println("userId:" + userId + "--->userName:" + userName);
		}
		
		SqlUtil.close(statement, conn);
	}

}
