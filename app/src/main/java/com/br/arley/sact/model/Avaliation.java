package com.br.arley.sact.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;


@Entity
public class Avaliation implements Parcelable, Comparable<Avaliation>{

    @PrimaryKey(autoGenerate = false)
    private String id;

    @ColumnInfo(name = "evaluator_id")
    @SerializedName("evaluator_id")
    private String evaluatorId;

    @ColumnInfo(name = "project_id")
    @SerializedName("project_id")
    private String projectId;

    private Project project;

    @ColumnInfo(name = "status")
    private String status;

    @ColumnInfo(name = "created_at")
    @SerializedName("created_at")
    private String createdAt;

    @ColumnInfo(name = "updated_at")
    @SerializedName("updated_at")
    private String updatedAt;

    public Avaliation(String id, String status){
        this.status = status;
        this.id = id;
    }


    protected Avaliation(Parcel in) {
        id = in.readString();
        evaluatorId = in.readString();
        projectId = in.readString();
        project = in.readParcelable(Project.class.getClassLoader());
        status = in.readString();
        createdAt = in.readString();
        updatedAt = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(evaluatorId);
        dest.writeString(projectId);
        dest.writeParcelable(project, flags);
        dest.writeString(status);
        dest.writeString(createdAt);
        dest.writeString(updatedAt);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Avaliation> CREATOR = new Creator<Avaliation>() {
        @Override
        public Avaliation createFromParcel(Parcel in) {
            return new Avaliation(in);
        }

        @Override
        public Avaliation[] newArray(int size) {
            return new Avaliation[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEvaluatorId() {
        return evaluatorId;
    }

    public void setEvaluatorId(String evaluatorId) {
        this.evaluatorId = evaluatorId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    @Override
    public String toString() {
        return "Avaliation{" +
                "id='" + id + '\'' +/*
                ", evaluatorId='" + evaluatorId + '\'' +
                ", projectId='" + projectId + '\'' +
                ", project=" + project +
                ", status='" + status + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +*/
                '}';
    }

    @Override
    public int compareTo(Avaliation o) {
        return this.status.equals("rated") ? 1 : this.status.equals("to_evaluate") ? -1 : 0;
    }
}
