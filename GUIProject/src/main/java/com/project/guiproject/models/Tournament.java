package com.project.guiproject.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Tournament {

    private int id;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private String description;
    private List<String> teams;

    private int maxTeams;


    public Tournament(int id, String name, LocalDate startDate, LocalDate endDate, String description, int maxTeams) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        this.maxTeams = maxTeams;
        this.teams = new ArrayList<>();
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getTeams() {
        return teams;
    }

    public void setTeams(List<String> teams) {
        this.teams = teams;
    }

    public int getMaxTeams() {
        return maxTeams;
    }

    public void setMaxTeams(int maxTeams) {
        this.maxTeams = maxTeams;
    }

    @Override
    public String toString() {
        return "Tournament{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", description='" + description + '\'' +
                ", teams=" + teams +
                ", maxTeams=" + maxTeams +
                '}';
    }

    // Additional methods to manage matches, stats, and other functionalities

}
