package Prosjekt;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.Statement;

public class registerGoal {
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private Connection myConn;
	private Statement myStmt;

	public registerGoal(Connection connection) {
		myConn = connection;
		System.out.println("MyConn:  "+ connection + "  " + myConn);
	}
	
	public void registerNewGoal(int ØvelsesId, int dagBokId){
		Date date = new Date();
		String timeStamp = sdf.format(date);
		try {
			System.out.println(myConn);
			myStmt = myConn.createStatement();
			String sql = "insert into Mål" + "(ØvelsesId, BokId, TidspunktSatt, TidspunktOppnådd)" 
					+ "values (" + ØvelsesId +","  +dagBokId +"," + "'" + timeStamp +"'" +"," +null + ")";			
			System.out.println(sql);
			myStmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String run(){
		registerNewGoal(1,1);
		try {
			myConn.close();
		} catch (SQLException e) {
			System.out.println("Can't close");
			e.printStackTrace();
		}
		return "Success!";
	}
}