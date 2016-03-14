package Prosjekt;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class MakeDiary {
	
	private Statement myStmt;
	private Connection myConn;
	
	public MakeDiary(Connection conn){
		myConn = conn;
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
	
	public String run(){
		makeDiary();
		return "Success!";
	}
	
}
