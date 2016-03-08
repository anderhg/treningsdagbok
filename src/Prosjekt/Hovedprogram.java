package Prosjekt;

public class Hovedprogram {

	public static void main(String[] args) {
		DatabaseConnection dbconn = new DatabaseConnection();
		DBOperations operations = new DBOperations(dbconn.getConnection());
		operations.insertExercise("Skråbenk", "Øvelse for skuldre/bryst");
		
		System.out.println(operations.showAll());
	}
}
