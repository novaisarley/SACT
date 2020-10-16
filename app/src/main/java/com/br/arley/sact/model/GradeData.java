package com.br.arley.sact.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GradeData {

    String avaliation_id;

    String comments;

    List<Grade> grades;

    public GradeData() {
        this.comments = " ";
    }

    public GradeData(String avaliation_id) {
        super();
        this.avaliation_id = avaliation_id;

    }

    public String getAvaliation_id() {
        return avaliation_id;
    }

    public void setAvaliation_id(String avaliation_id) {
        this.avaliation_id = avaliation_id;
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
