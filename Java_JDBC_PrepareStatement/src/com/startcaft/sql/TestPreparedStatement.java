package com.startcaft.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/***
 * Java.sql.PreparedStatement是Statement的子接口。
 * Statement可以执行静态的SQL语句，而PreparedStatement可以执行预编译的SQL
 * 
 * PreparedStatement相对于Statement来说，安全性更高(防止SQL注入)，效率更高。
 * 
 * @author wow
 *
 */
public class TestPreparedStatement {
	
	public static void main(String[] args) {

		//1.获取连接对象
		Connection con = SqlUtil.getConnection();
		
		//2.预编译的SQL语句，参数用?占位
		String insertString = "insert into myTable(userid,username) values(?,?)";
		
		PreparedStatement preparedStatement = null;
			
		try {
			//3.使用预编译的SQL语句来创建一个PreparedStatement对象
			preparedStatement = con.prepareStatement(insertString);
			//4.设置SQL语句中的参数，要明确数据类型,注意这里的索引是从1开始的啊
			preparedStatement.setInt(1, 444);
			preparedStatement.setString(2, "lvmengjie");
			//5.发送SQL语句
			int result = preparedStatement.executeUpdate();
			System.out.println("有" + result + "条记录被影响");
			SqlUtil.close(preparedStatement, con);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//在重新打印一次所有的数据
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
