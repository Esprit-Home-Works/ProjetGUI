package com.project.guiproject.models;

import java.util.Date;

public class Team {
    private int id;
    private int teamLeaderId;
    private String description;
    private String logo;

    public Team() {

    }

    public Team(int teamLeaderId, String description, String logo) {
        this.teamLeaderId = teamLeaderId;
        this.description = description;
        this.logo = logo;
    }

    public Team(int id, int teamLeaderId, String description, String logo) {
        this.id = id;
        this.teamLeaderId = teamLeaderId;
        this.description = description;
        this.logo = logo;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTeamLeaderId() {
        return teamLeaderId;
    }

    public void setTeamLeaderId(int teamLeaderId) {
        this.teamLeaderId = teamLeaderId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    @Override
    public String toString() {
        return "Team [id=" + id + ", teamLeaderId=" + teamLeaderId + ", description=" + description + ", logo=" + logo + "]";
    }
}
