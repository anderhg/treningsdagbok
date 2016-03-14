package Prosjekt;
import java.sql.*;
import java.util.Date;
import java.util.Scanner;
import java.text.SimpleDateFormat;

public class registerWorkout {
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private Statement myStmt;
	private Connection myConn;
	private static int diaryID;
	public registerWorkout(Connection con, int diaryID){
		diaryID = diaryID;
		myConn=con;
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
	
	public String run(){
		Scanner sc = new Scanner(System.in);
		System.out.println("What was the duration of the workout: \n");
		System.out.println("How was your shape (1-10): \n");
		System.out.println("How did you perform on todays workout: \n");
		System.out.println();
		System.out.println();
		return "Success";
	}
}
