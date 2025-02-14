package fr.cesi.java.rush.model;

public class Learner {
    private String lastName = null;
    private String firstName = null;
    private String email = null;
    private String phone = null;
    private Integer absence = null;
    private String promotion = null;
    private Boolean isDelegate;

    public Learner() {
        this.isDelegate = false;
    }

    public Learner(String lastName, String firstName, String email, String phone, String promotion, Integer absence, Boolean isDelegate) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.phone = phone;
        this.promotion = promotion;
        this.absence = absence;
        this.isDelegate = isDelegate;
    }

    public String getLastName() {
        return lastName;
    }

    public Learner setLastName(String lastName) {
        this.lastName = lastName;

        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public Learner setFirstName(String firstName) {
        this.firstName = firstName;

        return this;
    }

    public String getEmail() {
        return email;
    }

    public Learner setEmail(String email) {
        this.email = email;

        return this;
    }

    public String getPhone() {
        return phone;
    }

    public Learner setPhone(String phone) {
        this.phone = phone;

        return this;
    }

    public String getPromotion() {
        return promotion;
    }

    public Learner setPromotion(String promotion) {
        this.promotion = promotion;

        return this;
    }

    public Integer getAbsence() {
        return absence;
    }

    public Learner setAbsence(Integer absence) {
        this.absence = absence;

        return this;
    }

    public Boolean getIsDelegate() {
        return isDelegate;
    }

    public Learner setIsDelegate(Boolean isDelegate) {
        this.isDelegate = isDelegate;

        return this;
    }
}
