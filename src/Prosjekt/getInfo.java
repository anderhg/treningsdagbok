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
	
	public void getLog(){
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
		System.out.println(ret); 	
	}
	
	public void getTopLifts() {
		String output ="";
		
		try {
			myStmt = myConn.createStatement();
			ResultSet myRs = myStmt.executeQuery("SELECT (Belastning*Repetisjoner) AS totallift, øvelser.navn as navn FROM øvelser, øktøvelse JOIN treningsøkt USING(ØktId) WHERE treningsøkt.Treningsdagbok_BokId = 1 AND øvelser.ØvelsesId = øktøvelse.ØvelsesId ORDER BY (Belastning*Repetisjoner) DESC LIMIT 10;");
			while (myRs.next()){
				output += myRs.getString("totallift") + " kg " + myRs.getString("navn") + "\n";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Top 10 beste løft gjennom tidene:\n" + output);
		
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
					+ " Treningsdagbok_BokId = " + bokId + ";");
			while (myRs.next()){
				num_workouts = myRs.getString("COUNT(ØktId)");
				num_hours = myRs.getString("SUM(Varighet)");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			myStmt = myConn.createStatement();
			ResultSet myRs = myStmt.executeQuery("SELECT SUM(Belastning*Repetisjoner) AS totaltløftet, COUNT(ØktId) as totalset, SUM(Repetisjoner) AS totalrepetisjoner FROM treningsøkt JOIN øktøvelse USING(ØktId) WHERE Dato >= DATE_ADD(CURDATE(), INTERVAL -30 DAY) AND treningsøkt.Treningsdagbok_BokId =" + bokId + ";");
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
		System.out.println("\n");
		
	}

	public void run() {
		getLog();
		getSummedInfo();
		getTopLifts();
	}

}
