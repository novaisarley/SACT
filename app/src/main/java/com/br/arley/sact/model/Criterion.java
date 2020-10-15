package com.br.arley.sact.model;

public class Criterion {
    String id;
    String title;
    String grade;

    public Criterion(){

    }

    public Criterion(String id, String title){
        this.id = id;
        this.title = title;
        this.grade = "6.0";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "\n\tCriterion{" +
                "\n\t\tid='" + id + "'\n,"  +
                "\t\t title='" + title + "',\n" +
                "\t\t grade='" + grade + "'\n"  +
                "\t}"+"\n";
    }
}
