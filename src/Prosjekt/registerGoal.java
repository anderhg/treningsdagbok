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
	}
	
	public void registerNewGoal(int ØvelsesId, int dagBokId, String Beskrivelse, int Belastning, int Repetisjoner){
		Date date = new Date();
		String timeStamp = sdf.format(date);
		try {
			System.out.println(myConn);
			myStmt = myConn.createStatement();
			String sql = "insert into Mål" + "(ØvelsesId, BokId, TidspunktSatt, TidspunktOppnådd, Beskrivelse, Belastning, Repetisjoner)" 
					+ "values (" +  ØvelsesId +","  +dagBokId +"," + "'" + timeStamp +"'" +"," +null + "," + "'" + Beskrivelse +"'" +"," + "'" + Belastning +"'" +"," + "'" + Repetisjoner +"'" + ")";			
			myStmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String run(int ØvelsesId, int dagBokId, String Beskrivelse, int Belastning, int Repetisjoner){
		registerNewGoal(ØvelsesId,dagBokId, Beskrivelse, Belastning, Repetisjoner);
		try {
			myConn.close();
			System.out.println("Connection closed");
		} catch (SQLException e) {
			System.out.println("Can't close");
			e.printStackTrace();
		}
		return "Success! You now registered a new goal";
	}
}