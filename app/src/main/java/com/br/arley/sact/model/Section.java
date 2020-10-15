package com.br.arley.sact.model;

import java.util.ArrayList;

public class Section {
    String title;
    ArrayList<Criterion> criterionList;

    public Section(){

    }

    public Section(String title, ArrayList<Criterion> criterionList) {
        this.title = title;
        this.criterionList = criterionList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<Criterion> getCriterionList() {
        return criterionList;
    }

    public void setCriterionList(ArrayList<Criterion> criterionList) {
        this.criterionList = criterionList;
    }
}
