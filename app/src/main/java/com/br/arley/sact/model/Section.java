package com.br.arley.sact.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Section{
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Section section = (Section) o;
        return title.equals(section.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, criterionList);
    }

    @Override
    public String toString() {
        return "\n"+ "Section{" +
                "title='" + title + "',\n" +
                " criterionList=" + criterionList +
                '}' + "\n";
    }

}
