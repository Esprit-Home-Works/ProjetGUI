package com.project.guiproject.models;

public class MatchTeam {
    private int id;
    private int matchId;
    private int teamId;

    public MatchTeam() {

    }

    public MatchTeam(int matchId, int teamId) {
        this.matchId = matchId;
        this.teamId = teamId;
    }

    public MatchTeam(int id, int matchId, int teamId) {
        this.id = id;
        this.matchId = matchId;
        this.teamId = teamId;
    }

    // Ajouter les getters et setters nécessaires

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    @Override
    public String toString() {
        return "MatchTeam [id=" + id + ", matchId=" + matchId + ", teamId=" + teamId + "]";
    }
}
