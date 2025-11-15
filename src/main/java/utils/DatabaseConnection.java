/**
* Clase BdConfig.java
*
* @author ADRIAN IGLESIAS RIÑO
* @version 1.0
*/


package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

	private static DatabaseConnection databaseConnection;
	private Connection connection;
	private static Config config = new Config();
	private static final String URL = config.getProperty("url");
	private static final String USER = config.getProperty("usubd");
	private static final String PASSWORD =config.getProperty("passbd");
	
	private DatabaseConnection() {
		
		try {
			this.connection = DriverManager.getConnection(URL,USER,PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static DatabaseConnection getInstance() {
		if(null == databaseConnection) {
			databaseConnection = new DatabaseConnection();
		}
		return databaseConnection;
	}
	
	public Connection getConnection() {
		return connection;
	}
}
