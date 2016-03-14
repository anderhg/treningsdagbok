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
					"SELECT COUNT(ØktId), SUM(Varighet), SUM(øktøvelse.Belastning), SUM(øktøvelse.Repetisjoner), SUM(øktøvelse.ØktId)"
					+ "FROM treningsøkt"
					+ "WHERE Dato >= DATE_ADD(CURDATE(), INTERVAL -30 DAY) AND"
					+ "Treningsdagbok_BokId = " + bokId + " AND ("
						+ "SELECT *"
						+ "FROM øktøvelse"
						+ "WHERE øktøvelse.ØktId = treningsøkt.ØktId");
			while (myRs.next()){
				num_workouts = myRs.getString("COUNT(ØktId)");
				num_hours = myRs.getString("SUM(Varighet)");
				num_sets = myRs.getString("SUM(øktøvelse.ØktId)");
				num_reps = myRs.getString("SUM(øktøvelse.Repetisjoner)");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Antall økter: " + num_workouts);
		System.out.println("Antall minutter: " + num_hours);
		System.out.println("Antall sett: " + num_sets);
		System.out.println("Antall reps: " + num_reps);
		
	}

	public void run() {
		//System.out.println("Test");
		System.out.println(getLog());
		getSummedInfo();
	}

}
