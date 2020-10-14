package com.br.arley.sact.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class AuthData {
    private Evaluator evaluator;

    private String token;

    public Evaluator getEvaluator() {
        return evaluator;
    }

    public void setEvaluator(Evaluator value) {
        this.evaluator = value;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String value) {
        this.token = value;
    }

}



