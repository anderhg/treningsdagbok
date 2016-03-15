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
		this.bokId = bokId;
	}
	
	//Get-metoder
	
	public String getLog(){
		String ret = "";
		try {
			myStmt = myConn.createStatement();
			ResultSet myRs = myStmt.executeQuery("Select * from treningsøkt WHERE Treningsdagbok_BokId = " + bokId);
			while (myRs.next()){
				ret += "" + myRs.getString("Dato") + ": " + myRs.getString("Notat") + "\n";
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return ret; 	
	}
	
	public void getSummedInfo(){
		String num_workouts = "";
		String num_hours = "";
		String num_sets = "";
		String num_reps = "";
		String total_lifted = "";
		
		try {
			myStmt = myConn.createStatement();
			ResultSet myRs = myStmt.executeQuery(
					"SELECT COUNT(ØktId), SUM(Varighet)"
					+ " FROM treningsøkt"
					+ " WHERE Dato >= DATE_ADD(CURDATE(), INTERVAL -30 DAY) AND"
					+ " Treningsdagbok_BokId = " + bokId + "");
			while (myRs.next()){
				num_workouts = myRs.getString("COUNT(ØktId)");
				num_hours = myRs.getString("SUM(Varighet)");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			myStmt = myConn.createStatement();
			ResultSet myRs = myStmt.executeQuery("SELECT SUM(Belastning*Repetisjoner) AS totaltløftet, COUNT(ØktId) as totalset, SUM(Repetisjoner) AS totalrepetisjoner FROM treningsøkt JOIN øktøvelse USING(ØktId) WHERE Dato >= DATE_ADD(CURDATE(), INTERVAL -30 DAY) AND treningsøkt.Treningsdagbok_BokId = 1;");
			while (myRs.next()){
				total_lifted = myRs.getString("totaltløftet");
				num_sets = myRs.getString("totalset");
				num_reps = myRs.getString("totalrepetisjoner");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("Antall økter: " + num_workouts);
		System.out.println("Antall minutter: " + num_hours);
		System.out.println("Totalt antall repetisjoner: " + num_reps);
		System.out.println("Totalt antall set: " + num_sets);
		System.out.println("Totalt antall kg løftet: " + total_lifted);
		
	}

	public void run() {
		//System.out.println("Test");
		System.out.println(getLog());
		getSummedInfo();
	}

}
