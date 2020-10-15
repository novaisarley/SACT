package com.br.arley.sact.model;

public class Question {

    String id;

    String section;

    String criterion;

    String created_at;

    String updated_at;

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

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    @Override
    public String toString() {
        return "\nQuestion{\n" +
                "  id='" + id + "'\n" +
                ", section='" + section + "'\n" +
                ", criterion='" + criterion + "'\n" +
                ", created_at='" + created_at + "'\n" +
                ", updated_at='" + updated_at + "'\n" +
                "}\n";
    }
}
