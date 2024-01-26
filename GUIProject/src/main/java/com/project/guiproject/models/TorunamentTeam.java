package com.project.guiproject.models;

import java.util.Date;

import java.util.Date;
import java.util.List;

public class TournamentTeam {
    private int id;
    private int tournamentId;
    private int teamId;

    public TournamentTeam() {

    }

    public TournamentTeam(int tournamentId, int teamId) {
        this.tournamentId = tournamentId;
        this.teamId = teamId;
    }

    public TournamentTeam(int id, int tournamentId, int teamId) {
        this.id = id;
        this.tournamentId = tournamentId;
        this.teamId = teamId;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(int tournamentId) {
        this.tournamentId = tournamentId;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    @Override
    public String toString() {
        return "TournamentTeam [id=" + id + ", tournamentId=" + tournamentId + ", teamId=" + teamId + "]";
    }
}
