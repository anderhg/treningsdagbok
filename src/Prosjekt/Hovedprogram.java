package Prosjekt;

public class Hovedprogram {

	public static void main(String[] args) {
		DatabaseConnection dbconn = new DatabaseConnection();
		DBOperations operations = new DBOperations(dbconn.getConnection());
		operations.insertExercise("Markløft", "Baseøvelse for rygg/ben");
		operations.insertExercise("Markløft", "Baseøvelse for rygg/ben");
		operations.insertExercise("Markløft", "Baseøvelse for rygg/ben");
		operations.insertExercise("Markløft", "Baseøvelse for rygg/ben");
		operations.insertExercise("Markløft", "Baseøvelse for rygg/ben");
		operations.insertExercise("Markløft", "Baseøvelse for rygg/ben");
		System.out.println(operations.showAll());
		dbconn.closeConnection();
	}
}
