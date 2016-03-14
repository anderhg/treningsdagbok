package Prosjekt;
import java.util.Scanner;

public class Hovedprogram {

	public static void main(String[] args) {
		boolean Con = true;
		DatabaseConnection dbconn = new DatabaseConnection();
		System.out.println("dbconn: " + dbconn);
		System.out.println("Welcome to the Personal WorkOutDiary©. Please choose the wanted action: \n\n   1. Make diary\n   2. Register workout\n   3. Register excersice\n   4. Register goal\n   5. Manage categories\n   6. Get info\n   7. Exit");
		while(Con){
			Scanner sc = new Scanner(System.in);
			try{
				Thread.sleep(1000);
			}
			catch(InterruptedException e){
				Thread.currentThread().interrupt();
			}
			int action = Integer.parseInt(sc.nextLine());
			switch (action) {
			case 1: MakeDiary d = new MakeDiary(dbconn.getConnection());
					sc.close();
					System.out.println(d.run());
					break;
			case 2: registerWorkout w = new registerWorkout(dbconn.getConnection());
					sc.close();
					w.run();
					break;
			case 3: registerExercise e  = new registerExercise(dbconn.getConnection());
					sc.close();	
					e.run();
					break;
			case 4: registerGoal g = new registerGoal(dbconn.getConnection());
					sc.close();	
					System.out.println(g.run());
					System.out.println("Finished");
					break;
			case 5: manageCategories c = new manageCategories(dbconn.getConnection());
					sc.close();
					c.run();
					break;
			case 6: getInfo i = new getInfo(dbconn.getConnection());
					sc.close();
					i.run();
					break;
			case 7: Con = false; 
					dbconn.closeConnection();
					break;
			}
		}
	}
}
