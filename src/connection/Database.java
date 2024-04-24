package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Database {
	public Connection connection;
	public Statement statement;
	private final String USER = "root";
	private final String PASSWORD = "";
	private final String DATABASE = "pt_pudding";
	private final String HOST = "localhost:3306";
	private final String CONNECT = String.format("jdbc:mysql://%s/%s", HOST, DATABASE);
	
	public Database() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(CONNECT, USER, PASSWORD);
			statement = connection.createStatement();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void createTable() {
		String query = "CREATE TABLE IF NOT EXISTS Menus("
				+ "Menu_Code CHAR(6) NOT NULL,"
				+ "Menu_Name VARCHAR(70) NOT NULL,"
				+ "Menu_Price VARCHAR(50) NOT NULL,"
				+ "Menu_Stock INT NOT NULL"
				+ ")";
		try {
			statement.execute(query);	
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
