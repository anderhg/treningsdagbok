package Prosjekt;

import java.sql.*;
import java.util.Date;
import java.text.SimpleDateFormat;


public class DBOperations {
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private Statement myStmt;
	private Connection myConn;
	
	public DBOperations(Connection conn) {
		myConn = conn;
	}
	
	public void insertExercise(String name, String descr){
		try {
		myStmt = myConn.createStatement();
		String sql = "insert into øvelser" + "(Navn, Beskrivelse)" 
				+ "values ( '" + name+"', '" +descr +"')";
		
			myStmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void insertWorkout(int duration, int shape, int performance, String note, int diaryID ){
		Date date = new Date();
		String timeStamp = sdf.format(date);
		try {
			myStmt = myConn.createStatement();
			String sql = "insert into treningsøkt" + 
			"(Dato, Varighet, PersonligForm, PersonligPrestasjon, Notat, Treningsdagbok)" 
					+ "values (" +timeStamp+ "," +duration +"," + shape+ "," + performance +
					", '" +note + "'," + diaryID + " )";
			
				myStmt.executeUpdate(sql);
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	public String showAll(){
		String ret = "";
		try {
			myStmt = myConn.createStatement();
			ResultSet myRs = myStmt.executeQuery("Select * from øvelser");
			while (myRs.next()){
			ret += "" + myRs.getString("navn") + ", " + myRs.getString("beskrivelse") + "\n";
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return ret; 
			
	}
}
