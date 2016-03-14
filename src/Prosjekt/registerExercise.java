package Prosjekt;

import java.sql.*;
import java.util.Scanner;


public class registerExercise {
	private Statement myStmt; 
	private Connection myConn;
	
	public registerExercise(Connection con){
		myConn=con;
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
	public boolean checkName(String name){
		String ret = "";
		try {
			myStmt = myConn.createStatement();
			ResultSet myRs = myStmt.executeQuery("Select * from øvelser");
			while (myRs.next()){
				ret = myRs.getString("navn");
				if (ret.equalsIgnoreCase(name)){
					return true;
				}
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return false;
	}
	
	public String run(){
		Scanner sc = new Scanner(System.in);
		System.out.println("What's the name of the exercise: \n");
		String name = sc.nextLine();
		while (checkName(name)){
			System.out.println("That name is take, try a different one\n");
			System.out.println("What's the name of the exercise: \n");
			name = sc.nextLine();
		}
		
		System.out.println("Give a short description of the exercise: \n");
		String desc = sc.nextLine();
		insertExercise(name, desc);
		sc.close();
		return "Success";
	}
}

