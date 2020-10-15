package com.br.arley.sact.model;

public class Criterion {
    String title;
    String grade;

    public Criterion(){

    }

    public Criterion(String title, String grade) {
        this.title = title;
        this.grade = grade;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
