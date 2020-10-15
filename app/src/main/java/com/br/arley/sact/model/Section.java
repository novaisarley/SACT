package com.br.arley.sact.model;

import java.util.ArrayList;
import java.util.List;

public class Section {
    String title;
    List<Criterion> criterionList;

    public Section(){

    }

    public Section(String title){
        this.title = title;
    }

    public Section(String title, List<Criterion> criterionList) {
        this.title = title;
        this.criterionList = criterionList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Criterion> getCriterionList() {
        return criterionList;
    }

    public void setCriterionList(List<Criterion> criterionList) {
        this.criterionList = criterionList;
    }

    @Override
    public String toString() {
        return "\n"+ "Section{" +
                "title='" + title + "',\n" +
                " criterionList=" + criterionList +
                '}' + "\n";
    }
}
