package fr.cesi.java.rush.service;

import fr.cesi.java.rush.model.Learner;
import fr.cesi.java.rush.repository.LearnerRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class LearnerService {

    LearnerRepository _learnerRepository = LearnerRepository.getInstance();

    public void listLearners() {
        Scanner scanner = new Scanner(System.in);
        List<Learner> learners = _learnerRepository.getLearners();

        // TODO Demander et traiter le cas où l'utilisateur veut trier les apprenants par lastname, firstname, absence.

        this.displayLearners(learners);
    }

    public void listLearnersByPromotion() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the promotion of the learners:");
        String promotion = scanner.nextLine();

        List<Learner> learners = _learnerRepository.getLearners();

        List<Learner> learnersByPromotion = learners.stream()
                .filter(l -> l.getPromotion().equals(promotion))
                .toList();

        this.displayLearners(learnersByPromotion);
    }

    public void getPromotionAbsenteeismRate() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the promotion of the learners:");
        String promotion = scanner.nextLine();

        List<Learner> learners = _learnerRepository.getLearners();

        List<Learner> learnersByPromotion = learners.stream()
                .filter(l -> l.getPromotion().equals(promotion))
                .toList();

        Integer totalAbsence = learnersByPromotion.stream()
                .map(Learner::getAbsence)
                .reduce(0, Integer::sum); // Le map combiné au reduce permet de faire la somme de tous les attributs absence de tous les apprenants

        int totalLearners = learnersByPromotion.size();

        double absenteeismRate = (double) totalAbsence / totalLearners * 100;

        System.out.println("The absenteeism rate for the promotion " + promotion + " is " + absenteeismRate + "%.");
    }

    public void addLearner() {
        Scanner scanner = new Scanner(System.in);

        // TODO Valider toutes les entrées de l'utilisateur
        System.out.println("Enter the last name of the learner:");
        String lastName = scanner.nextLine();
        System.out.println("Enter the first name of the learner:");
        String firstName = scanner.nextLine();
        System.out.println("Enter the email of the learner:");
        String email = scanner.nextLine();
        System.out.println("Enter the phone of the learner:");
        String phone = scanner.nextLine();
        System.out.println("Enter the promotion of the learner:");
        String promotion = scanner.nextLine();
        System.out.println("Enter the absence of the learner:");
        Integer absence = scanner.nextInt();
        System.out.println("Is the learner a delegate? (true/false)");
        Boolean isDelegate = scanner.nextBoolean();

        Learner learner = new Learner(lastName, firstName, email, phone, promotion, absence, isDelegate);

        List<Learner> learners = _learnerRepository.getLearners();

        if (learners.stream()
                .anyMatch(l -> l.getEmail().equals(learner.getEmail()))) {
            System.out.println("A learner with this email already exists.");
            return;
        }

        if (learners.stream()
                .anyMatch(l -> l.getFirstName().equals(learner.getFirstName()) && l.getLastName().equals(learner.getLastName()))
        ) {
            System.out.println("A learner with this first name and last name already exists.");
            return;
        }

        try {
            _learnerRepository.addLearner(learner);
            System.out.println("The learner has been added successfully.");
        } catch (Exception e) {
            System.out.println("An error occurred while adding the learner.");
            System.out.println(e.getMessage());
        }
    }

    private void displayLearners(List<Learner> learners) {
        String leftAlignFormat = "| %-15s | %-15s | %-30s | %-15s | %-15s | %-15s | %-15s |%n";

        System.out.format("+-----------------+-----------------+--------------------------------+-----------------+-----------------+-----------------+-----------------+%n");
        System.out.format("| Last name       | First name      | Email                          | Phone           | Promotion       | Absence         | Is delegate     |%n");
        System.out.format("+-----------------+-----------------+--------------------------------+-----------------+-----------------+-----------------+-----------------+%n");

        for (Learner learner : learners) {
            System.out.format(leftAlignFormat, learner.getLastName(), learner.getFirstName(), learner.getEmail(), learner.getPhone(), learner.getPromotion(), learner.getAbsence(), learner.getIsDelegate());
        }

        System.out.format("+-----------------+-----------------+--------------------------------+-----------------+-----------------+-----------------+-----------------+%n");
    }
}
