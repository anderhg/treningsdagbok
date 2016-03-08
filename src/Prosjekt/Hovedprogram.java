package Prosjekt;
import java.util.Scanner;

public class Hovedprogram {

	public static void main(String[] args) {
		boolean Con = true;
		DatabaseConnection dbconn = new DatabaseConnection();
<<<<<<< HEAD
		Scanner sc = new Scanner(System.in);
		System.out.println("Welcome to the Personal WorkOutDiary©. Please choose the wanted action: \n\n   1. Make diary\n   2. Register workout\n   3. Register excersice\n   4. Register goal\n   5. Manage categories\n   6. Get info\n   7. Exit");
		while(sc.hasNext()){
=======
		System.out.println("Welcome to the Personal WorkOutDiary©. Please choose the wanted action: \n\n   1. Make diary\n   2. Register workout\n   3. Register excersice\n   4. Register goal\n   5. Manage categories\n   6. Get info\n   7. Exit");
		while(Con){
			Scanner sc = new Scanner(System.in);
>>>>>>> master
			int action = sc.nextInt();
			switch (action) {
			case 1: MakeDiary d = new MakeDiary(dbconn.getConnection());
					d.run();
<<<<<<< HEAD
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
=======
			case 2: registerWorkout w = new registerWorkout(dbconn.getConnection());
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
			
>>>>>>> master
			}
			
			
		}
<<<<<<< HEAD
=======
		
>>>>>>> master
	}
}
