package Demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DButil {
	public static Connection c;
	public static Connection getConnection()
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			c=DriverManager.getConnection("jdbc:mysql://localhost:3306/atmprocess","root","Buvanesh@123");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return c;
		
	}
}
