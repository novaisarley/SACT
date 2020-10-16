package com.br.arley.sact.model;

import com.google.gson.annotations.SerializedName;

public class Grade {

    String question_id;

    float grade;

    public Grade() {
    }

    public Grade(String question_id, float grade) {
        this.question_id = question_id;
        this.grade = grade;
    }

    public String getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(String question_id) {
        this.question_id = question_id;
    }

    public float getGrade() {
        return grade;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }
}
