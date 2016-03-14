package Prosjekt;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class getInfo {
	
	private Statement myStmt;
	private Connection myConn;
	private int bokId;
	
	public getInfo(Connection conn, int bokId) {
		myConn = conn;
	}
	
	//Get-metoder
	
	public String getLog(){
		String ret = "";
		try {
			myStmt = myConn.createStatement();
			ResultSet myRs = myStmt.executeQuery("Select * from treningsøkt where Treningsdagbok_BokId=" + bokId);
			while (myRs.next()){
			ret += "" + myRs.getString("Dato") + ": " + myRs.getString("Notat") + "\n";
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return ret; 
			
	}

	public void run() {
		System.out.println(getLog());
	}

}
