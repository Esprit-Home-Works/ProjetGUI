package com.project.guiproject.models;

import java.util.Date;
import java.util.List;

public class Tournament {
    private int id;
    private String name;
    private String description;
    private Date startDate;
    private Date endDate;
    private List<Team> teams;  // Supposons que chaque tournoi a une liste d'équipes participantes

    public Tournament(int id, String name, String description, Date startDate, Date endDate, List<Team> teams) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.teams = teams;
    }

    public Tournament(String name, String description, Date startDate, Date endDate, List<Team> teams) {
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.teams = teams;
    }
    public Tournament( ) {

    }

    @Override
    public String toString() {
        return "Tournament1{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", teams=" + teams +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }
}
