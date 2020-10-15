package com.br.arley.sact.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GradeData {

    @SerializedName("avaliation_id")
    String avaliationId;

    String comments;

    List<Grade> grades;

    public String getAvaliationId() {
        return avaliationId;
    }

    public void setAvaliationId(String avaliationId) {
        this.avaliationId = avaliationId;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public List<Grade> getGrades() {
        return grades;
    }

    public void setGrades(List<Grade> grades) {
        this.grades = grades;
    }
}
