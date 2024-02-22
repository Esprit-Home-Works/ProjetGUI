package com.project.guiproject.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import javax.persistence.Entity;

@Entity
@Table(name = "team")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String teamName;

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    private String teamDescription;
    public String getTeamDescription() {
        return teamDescription;
    }

    public void setTeamDescription(String teamDescription) {
        this.teamDescription = teamDescription;
    }

    private String teamlocation;
    public void setTeamlocation(String teamlocation) {
        this.teamlocation = teamlocation;
    }

    public String getTeamlocation() {
        return teamlocation;
    }

    public Team() {

    }

    public Team(int id, String teamName, String teamDescription, String teamLocation) {
        this.id = id;
        this.teamName = teamName;
        this.teamDescription = teamDescription;
        this.teamlocation = teamLocation;
    }

    public Team(String teamName, String teamDescription, String teamLocation) {
        this.teamName = teamName;
        this.teamDescription = teamDescription;
        this.teamlocation = teamLocation;
    }


    public Team(int id, String teamDescription) {
        this.id = id;
        this.teamDescription = teamDescription;
    }

    @Override
    public String toString() {
        return "Team [id=" + id + ", teamName=" + teamName + ", teamDescription=" + teamDescription + ", teamlocation=" + teamlocation + "]";
    }


}
