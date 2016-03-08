package Prosjekt;


import java.sql.*;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;


public class DatabaseConnection {
	private MysqlDataSource dataSource;
	private Connection connection;
	
	public DatabaseConnection(){
		dataSource = new MysqlDataSource();
		dataSource.setUser("babf3142724ee9");
		dataSource.setPassword("1935c4f2");
		dataSource.setServerName("eu-cdbr-azure-north-d.cloudapp.net");
		dataSource.setDatabaseName("feelmybicep");
		
		try {
			connection = dataSource.getConnection();
		}
		catch(Exception e) {
			System.out.println("Connection error");
		}
	}
	
	public Connection getConnection() {
		return connection;
	}
	
	public void closeConnection() {
		try {
			connection.close();
		}
		catch(Exception e) {
			System.out.println("Can't close connection");
		}
	}

}