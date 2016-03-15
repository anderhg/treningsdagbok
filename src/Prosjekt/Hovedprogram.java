package Prosjekt;
import java.util.Scanner;

public class Hovedprogram {

	public static void main(String[] args) {
		Hovedprogram hp = new Hovedprogram();
		hp.run();
	}
	
	public void run(){
		int diaryID = 0;
		boolean Con = true;
		DatabaseConnection dbconn = new DatabaseConnection();
		Scanner sc = new Scanner(System.in);
		while(Con){
			System.out.println("Welcome to the Personal WorkOutDiary©. Please choose the wanted action: \n\n   1. Make diary\n   2. Register workout\n   3. Register excersice\n   4. Register goal\n   5. Manage categories\n   6. Get info\n   7. Exit");
			try{
				Thread.sleep(1000);
			}
			catch(InterruptedException e){
				Thread.currentThread().interrupt();
			}
			int action = Integer.parseInt(sc.nextLine());
			switch (action) {
			case 1: MakeDiary d = new MakeDiary(dbconn.getConnection());
					System.out.println(d.run());
					break;
			case 2: registerWorkout w = new registerWorkout(dbconn.getConnection(), diaryID);
					w.run();
					break;
			case 3: registerExercise e  = new registerExercise(dbconn.getConnection());
					System.out.println("What's the name of the exercise: \n");
					String name = sc.nextLine();
					while(e.checkName(name)){
						System.out.println("That name is take, try a different one\n");
						System.out.println("What's the name of the exercise: \n");
						name = sc.nextLine();
					}
					System.out.println("Give a short description of the exercise: \n");
					String desc = sc.nextLine();
					System.out.println(e.run(name, desc));
					break;
			case 4: registerGoal g = new registerGoal(dbconn.getConnection());
					System.out.println("Please enter the Exercise Id: ");
					int eid = sc.nextInt();
					System.out.println("Please enter the Diary Id: ");
					int did = sc.nextInt();
					System.out.println("Please enter load: ");
					int load = sc.nextInt();
					System.out.println("Please enter reps: ");
					int reps = sc.nextInt();
					System.out.println("Please provide a description: ");
					String useless = sc.nextLine();
					String des = sc.nextLine(); 
					System.out.println(g.run(eid, did, des, load, reps));
					break;
			case 5: manageCategories c = new manageCategories(dbconn.getConnection());
					c.run();
					break;
			case 6: getInfo i = new getInfo(dbconn.getConnection());
					i.run();
					break;
			case 7: Con = false; 
					dbconn.closeConnection();
					break;
			}
		}
		sc.close();
		dbconn.closeConnection();
	}
	
	
}



