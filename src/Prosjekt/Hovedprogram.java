package Prosjekt;
import java.util.Scanner;

public class Hovedprogram {

	public static void main(String[] args) {
		DatabaseConnection dbconn = new DatabaseConnection();
		Scanner sc = new Scanner(System.in);
		System.out.println("Welcome to the Personal WorkOutDiary©. Please choose the wanted action: \n\n   1. Make diary\n   2. Register workout\n   3. Register excersice\n   4. Register goal\n   5. Manage categories\n   6. Get info\n   7. Exit");
		while(sc.hasNext()){
			int action = sc.nextInt();
			switch (action) {
			case 1: MakeDiary d = new MakeDiary(dbconn.getConnection());
					d.run();
					break;
			case 2: MakeDiary w = new registerWorkout(dbconn.getConnection());
					w.run();
					break;
			case 3: MakeDiary d = new MakeDiary(dbconn.getConnection());
					d.run();
					break;
			case 4: MakeDiary d = new MakeDiary(dbconn.getConnection());
					d.run();
					break;
			case 5: MakeDiary d = new MakeDiary(dbconn.getConnection());
					d.run();
					break;
			case 6: MakeDiary d = new MakeDiary(dbconn.getConnection());
					d.run();
					break;
			case 7: ;//dbconn.closeConnection()
					System.out.println("Exited successfully");
					break;
			}
			
		}
	}
}
