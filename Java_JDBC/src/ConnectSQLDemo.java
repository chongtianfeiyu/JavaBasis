import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class ConnectSQLDemo {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String diverClassName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";//SQLSERVER JDBC驱动完整类名
		String urlString = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=JavaTestDB";//数据库连接字符串
		String userString = "sa";//数据库登录用户名
		String password = "5904395";//登录用户的密码
		
		try {
			//1.注册驱动
			Class.forName(diverClassName);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			//2.连接目标数据库
			Connection connection = DriverManager.getConnection(urlString, userString, password);
			//3.创建Statement对象
			Statement statement = connection.createStatement();
			//4.准备SQL语句
			String sqlString = "CREATE TABLE myTable(userId int primary key,userName varchar(50) not null)";
			//5.通过Statement对象想数据库发送SQL语句，并返回结果
			int result = statement.executeUpdate(sqlString);
			System.out.println("mtTable创建完毕");
			//5.关闭资源
			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
