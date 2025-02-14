package fr.cesi.java.rush.repository;

import fr.cesi.java.rush.database.DatabaseConnection;
import fr.cesi.java.rush.model.Learner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LearnerRepository {

    private static LearnerRepository instance;

    private LearnerRepository() {
    }

    public static LearnerRepository getInstance() {
        if (instance == null) {
            instance = new LearnerRepository();
        }

        return instance;
    }

    public void addLearner(Learner learner) {
        String request = "INSERT INTO learner (lastName, firstName, email, phone, promotion, absence, isDelegate) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getInstance();
             PreparedStatement statement = connection.prepareStatement(request)) {

            statement.setString(1, learner.getLastName());
            statement.setString(2, learner.getFirstName());
            statement.setString(3, learner.getEmail());
            statement.setString(4, learner.getPhone());
            statement.setString(5, learner.getPromotion());
            statement.setInt(6, learner.getAbsence());
            statement.setBoolean(7, learner.getIsDelegate());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Learner> getLearners() {
        String request = "SELECT * FROM learner";
        List<Learner> learners = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getInstance();
             PreparedStatement statement = connection.prepareStatement(request);
             ResultSet resultSet = statement.executeQuery()) {


            while (resultSet.next()) {
                String lastName = resultSet.getString("lastName");
                String firstName = resultSet.getString("firstName");
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone");
                String promotion = resultSet.getString("promotion");
                Integer absence = resultSet.getInt("absence");
                Boolean isDelegate = resultSet.getBoolean("isDelegate");

                Learner learner = new Learner(lastName, firstName, email, phone, promotion, absence, isDelegate);
                learners.add(learner);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return learners;
    }

    public Learner getLearnerByFirstnameAndLastname(String firstname, String lastname)
    {
        String request = "SELECT * FROM learner WHERE firstName = ? AND lastName = ?";
        Learner learner = null;

        try (Connection connection = DatabaseConnection.getInstance();
             PreparedStatement statement = connection.prepareStatement(request)) {

            statement.setString(1, firstname);
            statement.setString(2, lastname);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String lastName = resultSet.getString("lastName");
                    String firstName = resultSet.getString("firstName");
                    String email = resultSet.getString("email");
                    String phone = resultSet.getString("phone");
                    String promotion = resultSet.getString("promotion");
                    Integer absence = resultSet.getInt("absence");
                    Boolean isDelegate = resultSet.getBoolean("isDelegate");

                    learner = new Learner(lastName, firstName, email, phone, promotion, absence, isDelegate);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return learner;
    }

    public void deleteLearner() {
        // TODO
    }
}
