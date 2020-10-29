package com.br.arley.sact.model;

import com.google.gson.annotations.SerializedName;

public class Question {

    String id;

    String section;

    String criterion;

    @SerializedName("min_grade")
    float minGrade;

    @SerializedName("max_grade")
    float maxGrade;

    @SerializedName("created_at")
    String createdAt;

    @SerializedName("updated_at")
    String updatedAt;

    public Question(){}


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getCriterion() {
        return criterion;
    }

    public void setCriterion(String criterion) {
        this.criterion = criterion;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
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
        return "\nQuestion{\n" +
                "  id='" + id + "'\n" +
                ", section='" + section + "'\n" +
                ", criterion='" + criterion + "'\n" +
                ", min_grade='" + minGrade + "'\n" +
                ", max_grade='" + maxGrade + "'\n" +
                ", created_at='" + createdAt + "'\n" +
                ", updated_at='" + updatedAt + "'\n" +
                "}\n";
    }
}
