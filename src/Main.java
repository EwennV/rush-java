import fr.cesi.java.rush.model.Learner;
import fr.cesi.java.rush.repository.LearnerRepository;
import fr.cesi.java.rush.service.LearnerService;

import java.util.List;
import java.util.Scanner;

public class Main {
    LearnerRepository _learnerRepository;

    public static void main(String[] args) {
        Main main = new Main();
        main.selector();
    }

    public void selector() {
        Scanner scanner = new Scanner(System.in);
        LearnerService learnerService = new LearnerService();

        boolean stopSelector = false;

        while(!stopSelector) {
            this.displayMenu();

            int choice = scanner.nextInt();
            System.out.flush();

            switch (choice) {
                case 1:
                    learnerService.listLearners();
                    break;
                case 2:
                    learnerService.listLearnersByPromotion();
                    break;
                case 3:
                    learnerService.getPromotionAbsenteeismRate();
                    break;
                case 4:
                    System.out.println("Not implemented yet");
                    break;
                case 5:
                    System.out.println("Not implemented yet");
                    break;
                case 6:
                    System.out.println("Not implemented yet");
                    break;
                case 7:
                    stopSelector = true;
                    break;
            }
        }
    }

    private void displayMenu() {
        System.out.println("\n");
        System.out.println("===== MENU =====");
        System.out.println("1. List learners");
        System.out.println("2. List learners by Promotion");
        System.out.println("3. Get promotion absenteeism rate");
        System.out.println("4. Add learner");
        System.out.println("5. Update learner absence");
        System.out.println("6. Update learner");
        System.out.println("7. Delete learner");
        System.out.println("8. Exit");

        System.out.println("================");
        System.out.print("> ");
    }
}