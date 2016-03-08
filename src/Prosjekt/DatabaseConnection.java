package Prosjekt;


import java.sql.*;


public class DatabaseConnection {
	private String url = "jdbc:mysql://localhost:3306/databaseprosjekt?autoReconnect=true&useSSL=false";
	private String username = "root";
	private String password = "donald60";
	private Connection myConn;
	private Statement myStmt;
	
	public DatabaseConnection(){
		try{
		myConn = DriverManager.getConnection(this.url, this.username, this.password);
		
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public DatabaseConnection(String url, String username, String password){
		try{
			myConn = DriverManager.getConnection(url, username, password);
		}
		catch(Exception e){
			e.printStackTrace();
		}
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
	
	public String showAll(){
		String ret = "";
		try {
			myStmt = myConn.createStatement();
			ResultSet myRs = myStmt.executeQuery("Select * from category");
			while (myRs.next()){
			ret += "" + myRs.getString("category_id") + ", " + myRs.getString("name") + "\n";
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return ret; 
			
	}
	
	
	
	
}
