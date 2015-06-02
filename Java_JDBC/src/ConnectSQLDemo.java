import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class ConnectSQLDemo {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String diverClassName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";//SQLSERVER JDBC������������
		String urlString = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=JavaTestDB";//���ݿ������ַ���
		String userString = "sa";//���ݿ��¼�û���
		String password = "5904395";//��¼�û�������
		
		try {
			//1.ע������
			Class.forName(diverClassName);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			//2.����Ŀ�����ݿ�
			Connection connection = DriverManager.getConnection(urlString, userString, password);
			//3.����Statement����
			Statement statement = connection.createStatement();
			//4.׼��SQL���
			String sqlString = "CREATE TABLE myTable(userId int primary key,userName varchar(50) not null)";
			//5.ͨ��Statement���������ݿⷢ��SQL��䣬�����ؽ��
			int result = statement.executeUpdate(sqlString);
			System.out.println("mtTable�������");
			//5.�ر���Դ
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
