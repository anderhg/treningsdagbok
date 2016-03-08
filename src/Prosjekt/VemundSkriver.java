package Prosjekt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class VemundSkriver {
	
	private String url = "jdbc:mysql://localhost:3306/databaseprosjekt?autoReconnect=true&useSSL=false";
	private String username = "root";
	private String password = "oppland";
	private Connection myConn;
	private Statement myStmt;
	
	public VemundSkriver(){
		try{
		myConn = DriverManager.getConnection(this.url, this.username, this.password);
		
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	
	public void makeDiary(){
		try {
		myStmt = myConn.createStatement();
		String sql = "insert into Treningsdagbok values()";
		
			myStmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void clean(){
		try {
			myStmt = myConn.createStatement();
			String sql = "SELECT COUNT(*) AS ANT FROM Treningsdagbok";
			ResultSet result = myStmt.executeQuery(sql);
			int i = 0;
			while(result.next()){
				i = result.getInt("ANT");
			}
			for (int a = i; a>= 0; a--){
				String sql2 = "DELETE FROM Treningsdagbok WHERE bokId =" + a;
				myStmt.executeUpdate(sql2);
			}
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}

}
