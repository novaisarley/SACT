
package com.br.arley.sact.model;

import com.google.gson.annotations.SerializedName;

public class Evaluator {
    private String id;

    private String name;

    @SerializedName("occupation_area")
    private String occupationArea;

    private String institution;

    @SerializedName("phone_number")
    private String phoneNumber;

    private String email;

    private String cpf;

    private String status;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("updated_at")
    private String updatedAt;

    public String getId() { return id; }

    public void setId(String value) { this.id = value; }

    public String getName() { return name; }

    public void setName(String value) { this.name = value; }

    public String getOccupationArea() { return occupationArea; }

    public void setOccupationArea(String value) { this.occupationArea = value; }

    public String getInstitution() { return institution; }

    public void setInstitution(String value) { this.institution = value; }

    public String getPhoneNumber() { return phoneNumber; }

    public void setPhoneNumber(String value) { this.phoneNumber = value; }

    public String getEmail() { return email; }

    public void setEmail(String value) { this.email = value; }

    public String getCpf() { return cpf; }

    public void setCpf(String value) { this.cpf = value; }

    public String getStatus() { return status; }

    public void setStatus(String value) { this.status = value; }

    public String getCreatedAt() { return createdAt; }

    public void setCreatedAt(String value) { this.createdAt = value; }

    public String getUpdatedAt() { return updatedAt; }

    public void setUpdatedAt(String value) { this.updatedAt = value; }

    @Override
    public String toString() {
        return "Evaluator{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", occupationArea='" + occupationArea + '\'' +
                ", institution='" + institution + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", cpf='" + cpf + '\'' +
                ", status='" + status + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
