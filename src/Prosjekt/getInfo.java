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
	
	public String getSummedInfo(){
		String num_workouts = "";
		String num_hours = "";
		String num_sets = "";
		String num_reps = "";
		String total_lifted = "";
		try {
			myStmt = myConn.createStatement();
			ResultSet myRs = myStmt.executeQuery("Select COUNT(ØktId) as num_workouts from treningsøkt where Dato >= DATE_ADD(CURDATE(), INTERVAL -30 DAY) AND Treningsdagbok_BokId=" + bokId);
			while (myRs.next()){
				num_workouts += myRs.getString("num_workouts");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return num_workouts;	
		
	}

	public void run() {
		System.out.println(getLog());
		System.out.println(getSummedInfo());
	}

}
