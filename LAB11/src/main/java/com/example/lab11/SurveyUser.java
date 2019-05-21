package com.example.lab11;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class SurveyUser {

    @Id
    @GeneratedValue
    @Column(name = "userID")
    private int userID;

    @Column(name = "username")
    private String username;

    @Override
    public String toString() {
        return "SurveyUser{" +
                "userID=" + userID +
                ", username='" + username + '\'' +
                '}';
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
