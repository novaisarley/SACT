package com.br.arley.sact.model;

import com.google.gson.annotations.SerializedName;

public class Grade {

    @SerializedName("question_id")
    String questionId;

    float grade;

    public Grade() {
    }

    public Grade(String questionId, float grade) {
        this.questionId = questionId;
        this.grade = grade;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public float getGrade() {
        return grade;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "\n\t" + "Grade{" +  "\n" +
                "\t\tquestionId='" + questionId + "' ,\n" +
                "\t\tgrade='" + grade + "'\n" +
                "\t" + '}' + "\n";
    }
}
