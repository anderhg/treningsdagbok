package Prosjekt;
import java.sql.*;
import java.util.Date;
import java.util.Scanner;
import java.text.SimpleDateFormat;

public class registerWorkout {
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private Statement myStmt;
	private Connection myConn;
	private int bokId;
	
	public registerWorkout(Connection con, int bokId){
		this.bokId = bokId;
		myConn=con;
	}
	
	public void printExercises() {
		String ret = "";
		try {
			myStmt = myConn.createStatement();
			ResultSet myRs = myStmt.executeQuery("Select * from øvelser;");
			while (myRs.next()){
				ret += "" + myRs.getString("ØvelsesId") + " - " + myRs.getString("Navn") + " - " + myRs.getString("Beskrivelse") + "\n";
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		System.out.println(ret); 
	}
	
	public String getLastId() {
		String ret = "";
		try {
			myStmt = myConn.createStatement();
			ResultSet myRs = myStmt.executeQuery("Select MAX(ØktId) as ID FROM treningsøkt;");
			while (myRs.next()){
				ret = myRs.getString("ID");
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return ret;
	}
	
	public void insertWorkout(String duration, String shape, String performance, String note){
		Date date = new Date();
		String timeStamp = sdf.format(date);
		try {
			myStmt = myConn.createStatement();
			String sql = "INSERT INTO treningsøkt (Dato, Varighet, PeronligForm, PersonligPrestasjon, Notat, Treningsdagbok_BokId) "
					+ "values ('" + timeStamp + "', " +duration +", " + shape+ ", " + performance +
					", '" +note + "', " + bokId + ")";
				myStmt.executeUpdate(sql);
				System.out.println("Workout inserted\n");
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	public void insertExercise(String øktId, String øvelsesId, String belastning, String repetisjoner) {
		try {
			myStmt = myConn.createStatement();
			String sql = "INSERT INTO øktøvelse (ØktId, ØvelsesId, Belastning, Repetisjoner) "
					+ "values (" + øktId + ", " + øvelsesId + ", " + belastning + ", " + repetisjoner + ");";
				myStmt.executeUpdate(sql);
				System.out.println("Workout inserted\n");
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	public void run(){
		String duration = "";
		String shape = "";
		String performance = "";
		String note = "";
		
		String øvelseId = "";
		String øktId ="";
		String belastning = "";
		String repetisjoner = "";
		
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("What was the duration of the workout: \n");
		duration = sc.nextLine();
		System.out.println("How was your shape (1-10): \n");
		shape = sc.nextLine();
		System.out.println("How did you perform on today's workout: \n");
		performance = sc.nextLine();
		System.out.println("Your notes: \n");
		note = sc.nextLine();
		insertWorkout(duration, shape, performance, note);
		
		while(true) {
			System.out.println("Please state the workout's exercises. Press enter to finish. \n");
			System.out.println("Choose the exercise ID: \n");
			printExercises();
			System.out.println("Exercise ID: \n");
			øvelseId = sc.nextLine();
			if(øvelseId.equals("")) {
				break;
			}
			System.out.println("Insert weight: \n");
			belastning = sc.nextLine();
			System.out.println("Insert reps: \n");
			repetisjoner = sc.nextLine();
			øktId = getLastId();
			
			insertExercise(øktId, øvelseId, belastning, repetisjoner);
			
			System.out.println("Exercise inserted!\n");
		}
		System.out.println("Workout registrered!");
		sc.close();
	}
}
