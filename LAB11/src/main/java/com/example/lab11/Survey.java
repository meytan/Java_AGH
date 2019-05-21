package com.example.lab11;

import javax.persistence.*;

@Entity
@Table(name = "survey")
public class Survey {

    @Id
    @GeneratedValue
    private int surveyId;

    private String surveyTitle;
    private String question;
    private int userID;

    @Override
    public String toString() {
        return "Survey{" +
                "surveyId=" + surveyId +
                ", surveyTitle='" + surveyTitle + '\'' +
                ", question='" + question + '\'' +
                ", userID=" + userID +
                '}';
    }

    public int getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(int surveyId) {
        this.surveyId = surveyId;
    }

    public String getSurveyTitle() {
        return surveyTitle;
    }

    public void setSurveyTitle(String surveyTitle) {
        this.surveyTitle = surveyTitle;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}
