package com.revature.pojos;

import java.util.Objects;

public class User {
    private Integer userId;
    private String firstName;
    private String lastName;
    private String userPass;
    private String email;
    private boolean admin;

    public User() {

    }

    public User(Integer userId, String firstName, String lastName, String userPass, String email, boolean admin) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userPass = userPass;
        this.email = email;
        this.admin = admin;
    }

    public User(String firstName, String lastName, String userPass, String email, boolean admin) {
        this.userId = null;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userPass = userPass;
        this.email = email;
        this.admin = admin;
    }
    public User(String firstName, String lastName, String userPass, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userPass = userPass;
        this.email = email;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId)  && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(userPass, user.userPass) && Objects.equals(email, user.email) && Objects.equals(admin, user.admin);

    }

}
