package com.br.arley.sact.model;

public class Criterion {
    String title;
    String score;

    public Criterion(String title, String score) {
        this.title = title;
        this.score = score;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
