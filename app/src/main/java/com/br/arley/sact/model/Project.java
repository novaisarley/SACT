package com.br.arley.sact.model;

public class Project {
    private String name;
    private String classroom;
    private String course;
    private String[] members;

    public Project() {
    }

    public Project(String name, String classroom, String course, String[] members) {
        this.name = name;
        this.classroom = classroom;
        this.course = course;
        this.members = members;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public String[] getMembers() {
        return members;
    }

    public void setMembers(String[] members) {
        this.members = members;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }
}
