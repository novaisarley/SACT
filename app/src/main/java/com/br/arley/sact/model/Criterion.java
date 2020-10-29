package com.br.arley.sact.model;

import com.google.gson.annotations.SerializedName;

public class Criterion {

    String id;

    String title;

    String grade;

    @SerializedName("min_grade")
    float minGrade;

    @SerializedName("max_grade")
    float maxGrade;

    public Criterion(){

    }

    public Criterion(String id, String title, float minGrade, float maxGrade) {
        this.id = id;
        this.title = title;
        this.minGrade = minGrade;
        this.maxGrade = maxGrade;
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

    public float getMinGrade() {
        return minGrade;
    }

    public void setMinGrade(float minGrade) {
        this.minGrade = minGrade;
    }

    public float getMaxGrade() {
        return maxGrade;
    }

    public void setMaxGrade(float maxGrade) {
        this.maxGrade = maxGrade;
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
