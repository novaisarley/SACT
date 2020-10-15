package com.br.arley.sact.model;

import com.google.gson.annotations.SerializedName;

public class Grade {

    @SerializedName("question_id")
    String questionId;

    String grade;

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
