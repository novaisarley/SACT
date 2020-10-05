package com.br.arley.sact.model;

public class Evaluator {

    private String id;
    private String name;
    private String occupation_area;
    private String institution;
    private String phone_number;
    private String email;
    private String cpf;
    private String status;
    private String created_at;
    private String updated_at;

    public Evaluator() {
    }

    public Evaluator(String id, String name, String occupation_area, String institution, String phone_number, String email, String cpf, String status, String created_at, String updated_at) {
        this.id = id;
        this.name = name;
        this.occupation_area = occupation_area;
        this.institution = institution;
        this.phone_number = phone_number;
        this.email = email;
        this.cpf = cpf;
        this.status = status;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getOccupation_area() {
        return occupation_area;
    }

    public String getInstitution() {
        return institution;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public String getEmail() {
        return email;
    }

    public String getCpf() {
        return cpf;
    }

    public String getStatus() {
        return status;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }
}
