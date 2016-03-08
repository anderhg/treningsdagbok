package Prosjekt;
import java.util.Scanner;

public class Hovedprogram {

	public static void main(String[] args) {
		int diaryID = 0;
		boolean Con = true;
		DatabaseConnection dbconn = new DatabaseConnection();
		System.out.println("Welcome to the Personal WorkOutDiary©. Please choose the wanted action: \n\n   1. Make diary\n   2. Register workout\n   3. Register excersice\n   4. Register goal\n   5. Manage categories\n   6. Get info\n   7. Exit");
		while(Con){
			Scanner sc = new Scanner(System.in);
			int action = sc.nextInt();
			switch (action) {
			case 1: MakeDiary d = new MakeDiary(dbconn.getConnection());
					d.run();
			case 2: registerWorkout w = new registerWorkout(dbconn.getConnection(), diaryID);
					w.run();
			case 3: registerExercise e  = new registerExercise(dbconn.getConnection());
					e.run();
			case 4: registerGoal g = new registerGoal(dbconn.getConnection());
					g.run();
			case 5: manageCategories c = new manageCategories(dbconn.getConnection());
					c.run();
			case 6: getInfo i = new getInfo(dbconn.getConnection());
					i.run();
			case 7: Con = false; 
					//dbconn.closeConnection();
			
			}
			
			
		}
		
	}
}
