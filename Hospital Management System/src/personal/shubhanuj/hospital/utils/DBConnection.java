package personal.shubhanuj.hospital.utils;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnection {
	private static int connCount=0;
	private static Connection connection=null;
	
	public static Connection getConnection(){
		String driver="com.mysql.jdbc.Driver";
		String URL = "jdbc:mysql://localhost:3306/hospital";
		String username="root";
		String password="password";
		if (connection==null){
			try{		
				Class.forName(driver);		
				connection=DriverManager.getConnection(URL,username,password);
				connCount++;
				System.out.println("Connection count:"+connCount);
			}
			catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}	
		return connection;
	}  
}  


