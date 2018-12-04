import java.sql.*;
import java.awt.*;
import javax.swing.*;

public class DatabaseConnection {
	
	public Connection DatabaseConnector() {
		try{
		      String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
		      String DB_URL = "jdbc:oracle:thin:@cloud-34-133.eci.ucsb.edu:1521:XE";

		      Class.forName(JDBC_DRIVER);
		      System.out.println("Connecting to a selected database...");
		      Connection conn = DriverManager.getConnection(DB_URL, "ajitvijayakumar", "12Vijay99!");
		      return conn;
		    }
		    catch(SQLException se) {
		      se.printStackTrace();
		    }
		    catch(Exception e) {
		      e.printStackTrace();
		    }
		    return null;
	}
	public DatabaseConnection() {
		Connection conn = DatabaseConnector();
		System.out.println("Connection Established");
	}
	public ResultSet query(String input) {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = DatabaseConnector().createStatement();
			rs = stmt.executeQuery(input);
			rs.close();
		}
		catch(Exception e) {
			System.out.println("Error with query in backend");
			e.printStackTrace();
		}
		return rs;
	}
}
